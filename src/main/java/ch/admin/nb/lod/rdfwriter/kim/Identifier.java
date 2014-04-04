package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.DataField;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;

/**
 * <h4>Identifier</h4>
 * <p>
 * ISBN, ISSN, Kontrollnummern
 * </p>
 * 
 * @author Peter Schwery
 * 
 */
public class Identifier {

	/**
	 * @param listVariableField
	 * @param model
	 * @param id
	 */
	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {
		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			String tag = df.getTag();
			String data = "";

			Resource rdfSubject;
			Property rdfPredicate;
			Resource rdfObject;
			System.out.println(df.toString());
			switch (tag) {

			case "020":
				if (df.getSubfield('a') != null) {

					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);

					// Bindungsart, Preis etc. löschen
					data = data.replaceAll("^(\\d{9}X) .*", "$1");
					data = data.replaceAll("^(\\d{10}) .*", "$1");
					data = data.replaceAll("^(\\d{12}X) .*", "$1");
					data = data.replaceAll("^(\\d{13}) .*", "$1");

					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					rdfPredicate = model.createProperty(Constants.NS_BIBO_ISBN);
					model.add(rdfSubject, rdfPredicate, data);

				}
				break;

			case "022":
				if (df.getSubfield('a') != null) {

					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					rdfPredicate = model.createProperty(Constants.NS_BIBO_ISSN);
					model.add(rdfSubject, rdfPredicate, data);

				}
				break;

			case "024":

				if (df.getSubfield('2') != null) {
					if (df.getSubfield('2').getData().equals("DOI")) {
						if (df.getSubfield('a') != null) {

							data = df.getSubfield('a').getData();
							
							System.out.println(data);
							data = StringTool.cleanUp(data);
							rdfSubject = model
									.createResource(Constants.NS_HELVETICAT_BIB
											+ id);
							rdfPredicate = model
									.createProperty(Constants.NS_UMBEL_ISLIKE);
							model.add(rdfSubject, rdfPredicate, Constants.NS_DOI + data);

						}
					} else if (df.getSubfield('2').getData().equals("URN")) {
						if (df.getSubfield('a') != null) {

							data = df.getSubfield('a').getData();
							data = StringTool.cleanUp(data);
							rdfSubject = model
									.createResource(Constants.NS_HELVETICAT_BIB
											+ id);
							rdfPredicate = model
									.createProperty(Constants.NS_UMBEL_ISLIKE);
							model.add(rdfSubject, rdfPredicate, Constants.NS_URN + data);

						}

					}
				}
				break;

			case "030":
				if (df.getSubfield('a') != null) {

					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					rdfPredicate = model.createProperty(Constants.NS_BIBO_CODEN);
					model.add(rdfSubject, rdfPredicate, data);

				}
				break;

			case "035":
				if (df.getSubfield('a') != null) {

					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC.identifier, data);

				}
				break;

			}

		}
	}

}
