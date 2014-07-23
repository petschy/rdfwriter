package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.ControlField;
import org.marc4j.marc.Leader;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class MediaType {

	Boolean isBiboDocument;
	char typeOfRecord;
	char bibLevel;
	char multipart;
	String f008pos21;
	String f008pos7to14;

	public void toRdf(Leader leader, ControlField f007, ControlField f008,
			Model model, String id) {

		// Kein Leader -> Bib-Id in Log-Datei schreiben und abbrechen
		if (leader == null) {
			// TODO Log-Datei schreiben
			return;
		}

		// Kein Feld 008 -> Bib-Id in Log-Datei schreiben und abbrechen
		if (f008 == null) {
			// TODO Log-Datei schreiben
			return;
		}

		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);

		// Variable für bibo:document
		isBiboDocument = true;
		// Leader 06: Type of Record
		typeOfRecord = leader.getTypeOfRecord();
		// Leader 07: Bibliographic Level
		char[] implDefined1 = leader.getImplDefined1();
		bibLevel = implDefined1[0];
		// Leader 19: Multipart resource record level
		char[] implDefined2 = leader.getImplDefined2();
		multipart = implDefined2[2];
		// Feld 008/21
		f008pos21 = f008.getData().substring(21, 22);
		f008pos7to14 = f008.getData().substring(7, 15);

//		System.out.println("isBiboDocument: " + isBiboDocument);

		if (f007 == null) {
			// Kein Feld 007
			_checkLeader(model, rdfSubject);

		} else {
			_checkLeader(model, rdfSubject);

			String f007pos1 = f007.getData().substring(0, 1);
			String f007pos2 = f007.getData().substring(1, 2);
			String f007pos1to2 = f007.getData().substring(0, 2);

			if (f007pos1.equals("f")) {
				// "f" = Tactile material
				isBiboDocument = false;
				model.add(rdfSubject, DCTerms.medium, Constants.NS_LIB_BRAILLE);
			}

			if (f007pos1to2.equals("co")) {
				model.add(rdfSubject, DCTerms.medium,
						Constants.NS_RDA_CARRIERTYPE_ONLINERESOURCE);
//				System.out.println(f007pos1to2);

			} else if (f007pos1to2.equals("cr")) {
				model.add(rdfSubject, DCTerms.medium,
						Constants.NS_RDA_CARRIERTYPE_COMPUTER);

			} else if (f007pos1to2.equals("ou")) {
				model.add(rdfSubject, DCTerms.medium,
						Constants.NS_ISBD_MEDIATYPE_MULTIMEDIA);

			}

		}

		if (isBiboDocument == true) {
			model.add(rdfSubject, DCTerms.medium, Constants.NS_BIBO_DOCUMENT);

		}
	}

	private void _checkLeader(Model model, Resource rdfSubject) {
		if (typeOfRecord == 'a') {
			// 'a' = Language material

			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);

		} else if (typeOfRecord == 'g') {
			// 'g' = Projected medium

			isBiboDocument = false;
			model.add(rdfSubject, DCTerms.medium, Constants.NS_BIBO_AUDIOVISUAL);

		} else if (typeOfRecord == 'e') {
			// 'e' = Cartographic material

			isBiboDocument = false;
			model.add(rdfSubject, DCTerms.medium, Constants.NS_BIBO_MAP);

//			System.out.println("Map -> isBiboDocument: " + isBiboDocument);

		}

		if (bibLevel == 'a') {
			// 'a' = Monographic component part

			isBiboDocument = false;
			model.add(rdfSubject, DCTerms.medium, Constants.NS_BIBO_ARTICLE);

		} else if (bibLevel == 'b') {
			// 'b' = Serial component part

			isBiboDocument = false;
			model.add(rdfSubject, DCTerms.medium, Constants.NS_BIBO_ISSUE);

		} else if (bibLevel == 's') {
			// 's' = Serial

			model.add(rdfSubject, DCTerms.issued, f008pos7to14);
//			System.out.println("Issued: " + f008pos7to14);

			isBiboDocument = false;
			if (f008pos21 == "m") {
				model.add(rdfSubject, DCTerms.medium, Constants.NS_BIBO_SERIES);

			} else {
				model.add(rdfSubject, DCTerms.medium,
						Constants.NS_BIBO_PERIODICAL);

			}

		}

		if (multipart == 'a') {
			// 'a' = Set

			isBiboDocument = false;
			model.add(rdfSubject, DCTerms.medium, Constants.NS_BIBO_COLLECTION);

		}

	}

}
