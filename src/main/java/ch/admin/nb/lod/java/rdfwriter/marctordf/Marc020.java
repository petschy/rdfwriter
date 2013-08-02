package ch.admin.nb.lod.java.rdfwriter.marctordf;

import java.util.List;

import net.sourceforge.isbnhyphenappender.ISBNHyphenAppender;

import org.marc4j.marc.DataField;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.ISBN;
import ch.admin.nb.lod.rdfwriter.tools.MyFileWriter;
import ch.admin.nb.lod.rdfwriter.tools.Variables;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class Marc020 {

	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {

		ISBNHyphenAppender hyphenAppender = new ISBNHyphenAppender();
		Variables var = new Variables();

		// RDF-Triple
		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			
			if (df.getSubfield('a') != null) {
				// Unterfeld $a ist vorhanden

				// ISBN-Strings
				String isbn; // ISBN ohne Bindestriche
				String isbnRaw; // Feldinhalt 020 $a
				String isbn10;
				String isbn13;
				isbnRaw = df.getSubfield('a').getData();
				// System.out.println(isbnRaw);

				// Bindungsart, Preis etc. löschen
				isbn = isbnRaw.replaceAll("^(\\d{9}X) .*", "$1");
				isbn = isbnRaw.replaceAll("^(\\d{10}) .*", "$1");
				isbn = isbnRaw.replaceAll("^(\\d{12}X) .*", "$1");
				isbn = isbnRaw.replaceAll("^(\\d{13}) .*", "$1");

				// Korrektheit der ISBN prüfen
				Boolean boolIsbn = ISBN.checkISBN(isbn);
				
				if (boolIsbn) {
					// ISBN ist korrekt
					System.out.println(id);
					System.out.println(isbn);
					
					if (isbn.length() == 10) { 
						// ISBN 10
							
						// Triple bilden (ISBN ohne Bindestrich)
						rdfSubject = model
								.createResource(Constants.NS_HELVETICAT_BIB + id);
						rdfPredicate = model
								.createProperty(Constants.NS_BIBO_ISBN10);
						model.add(rdfSubject, rdfPredicate, isbn);

						// Triple bilden (ISBN mit Bindestrich)
						isbn10 = hyphenAppender.appendHyphenToISBN10(isbn);
						rdfSubject = model
								.createResource(Constants.NS_HELVETICAT_BIB + id);
						rdfPredicate = model
								.createProperty(Constants.NS_NBO_ISBN10);
						model.add(rdfSubject, rdfPredicate, isbn10);

					} else if (isbn.length() == 13) { 
						// ISBN 13

						// Triple bilden (ISBN13 ohne Bindestrich)
						rdfSubject = model
								.createResource(Constants.NS_HELVETICAT_BIB + id);
						rdfPredicate = model
								.createProperty(Constants.NS_BIBO_ISBN13);
						model.add(rdfSubject, rdfPredicate, isbn);

						// Triple bilden (ISBN13 mit Bindestrich); Namespace:
						// nbo
						isbn13 = hyphenAppender.appendHyphenToISBN13(isbn);
						rdfSubject = model
								.createResource(Constants.NS_HELVETICAT_BIB + id);
						rdfPredicate = model
								.createProperty(Constants.NS_NBO_ISBN13);
						model.add(rdfSubject, rdfPredicate, isbn13);

					}
				} else {					
					// ISBN ist nicht korrekt
					System.out.println(boolIsbn);

					System.out.println("ISBN nicht korrekt: " + isbn);
					MyFileWriter writer = new MyFileWriter();
					String message = id + "\t" + isbn;
					writer.write(message, var.bibHelveticatMarc020);
					// TODO: Log-File mit falscher ISBN schreiben
				}

			}
			
			if (df.getSubfield('z') != null) {
				// Unterfeld $z ist vorhanden
				
				String isbnInvalid;
				isbnInvalid = df.getSubfield('z').getData();
				
				// Triple bilden (ungültige ISBN ohne Bindestrich)
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				rdfPredicate = model
						.createProperty(Constants.NS_NBO_ISBN_INVALID);
				model.add(rdfSubject, rdfPredicate, isbnInvalid);

			}
			
			if (df.getSubfield('9') != null) {
				// Unterfeld $9 ist vorhanden
				
				String isbnInvalidWithHyphen;
				isbnInvalidWithHyphen = df.getSubfield('9').getData();
				
				// Triple bilden (ungültige ISBN ohne Bindestrich)
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				rdfPredicate = model
						.createProperty(Constants.NS_NBO_ISBN_INVALID_WITH_HYPHEN);
				model.add(rdfSubject, rdfPredicate, isbnInvalidWithHyphen);
				
			}
		}
	}

}
