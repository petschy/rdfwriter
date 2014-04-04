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

	// Feld 007 ist nicht vorhanden
	public void toRdf(Leader leader, ControlField f008, Model model, String id) {

		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);

		// Leader 06: Type of Record
		char typeOfRecord = leader.getTypeOfRecord();
		// Leader 07: Bibliographic Level
		char[] implDefined1 = leader.getImplDefined1();
		char bibLevel = implDefined1[0];
		// Leader 19: Multipart resource record level
		char[] implDefined2 = leader.getImplDefined2();
		char multipart = implDefined2[2];
		// 008/21: Type of continuing resource
		String data008 = f008.getData();
		String data008pos21 = data008.substring(21, 22);

		// Leader 06: Type of Record
		switch (typeOfRecord) {

		// a - Language material
		case 'a':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);

			break;
		// c - Notated music
		case 'c':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// d - Manuscript notated music
		case 'd':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// e - Cartographic material
		case 'e':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_MAP);
			break;
		// f - Manuscript cartographic material
		case 'f':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_MAP);
			break;
		// g - Projected medium
		case 'g':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIOVISUAL);
			break;
		// i - Nonmusical sound recording
		case 'i':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIODOCUMENT);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_MEDIATYPE_AUDIO);
			break;
		// j - Musical sound recording
		case 'j':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIODOCUMENT);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_MEDIATYPE_AUDIO);
			break;
		// k - Two-dimensional nonprojectable graphic
		case 'k':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_IMAGE);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// m - Computer file
		case 'm':

			break;
		// o - Kit
		case 'o':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIOVISUAL);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_ISBD_MEDIATYPE_MULTIMEDIA);
			break;
		// o - Medienkombination
		case 'p':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIOVISUAL);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_ISBD_MEDIATYPE_MULTIMEDIA);
			break;
		// r - Three-dimensional artifact
		case 'r':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// r - Manuscript
		case 't':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;

		default:
			break;
		}

		switch (bibLevel) {

		// Monographic component part
		case 'a':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_ARTICLE);
			break;
		// Serial component part
		case 'b':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_ISSUE);
			break;
		// Collection
		case 'c':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_COLLECTION);
			break;
		// Subunit
		case 'd':
			break;
		// Integrating resource
		case 'i':
			break;
		// Monograph/Item
		case 'm':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_DOCUMENT);
			break;
		// Serial
		case 's':

			switch (data008pos21) {
			// # - None of the following
			case " ":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// d - Updating database
			case "d":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// l - Updating loose-leaf
			case "l":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// m - Monographic series
			case "m":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_SERIES);
				break;
			// n - Newspaper
			case "n":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// p - Periodical
			case "p":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// w - Updating Web site
			case "w":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// | - No attempt to code
			case "|":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;

			default:
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;

			}

			break;

		default:
			break;

		}
	}

	// Feld 007 ist vorhanden
	public void toRdf(Leader leader, ControlField f007, ControlField f008,
			Model model, String id) {

		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);

		// Leader 06: Type of Record
		char typeOfRecord = leader.getTypeOfRecord();
		// Leader 07: Bibliographic Level
		char[] implDefined1 = leader.getImplDefined1();
		char bibLevel = implDefined1[0];
		// Leader 19: Multipart resource record level
		char[] implDefined2 = leader.getImplDefined2();
		char multipart = implDefined2[2];
		// 008/21: Type of continuing resource
		String data008 = f008.getData();
		String data008pos21 = data008.substring(21, 22);
		// 007
		String data007 = f007.getData();
		String data007pos00 = data007.substring(0, 1);
		String data007pos01 = data007.substring(1, 2);

		// Leader 06: Type of Record
		switch (typeOfRecord) {

		// a - Language material
		case 'a':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);

			break;
		// c - Notated music
		case 'c':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// d - Manuscript notated music
		case 'd':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// e - Cartographic material
		case 'e':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_MAP);
			break;
		// f - Manuscript cartographic material
		case 'f':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_MAP);
			break;
		// g - Projected medium
		case 'g':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIOVISUAL);
			break;
		// i - Nonmusical sound recording
		case 'i':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIODOCUMENT);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_MEDIATYPE_AUDIO);
			break;
		// j - Musical sound recording
		case 'j':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIODOCUMENT);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_MEDIATYPE_AUDIO);
			break;
		// k - Two-dimensional nonprojectable graphic
		case 'k':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_IMAGE);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// m - Computer file
		case 'm':

			break;
		// o - Kit
		case 'o':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIOVISUAL);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_ISBD_MEDIATYPE_MULTIMEDIA);
			break;
		// o - Medienkombination
		case 'p':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_AUDIOVISUAL);
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_ISBD_MEDIATYPE_MULTIMEDIA);
			break;
		// r - Three-dimensional artifact
		case 'r':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;
		// r - Manuscript
		case 't':
			model.add(rdfSubject, DCTerms.medium,
					Constants.NS_RDA_CARRIERTYPE_UNMEDIATED);
			break;

		default:
			break;
		}

		switch (bibLevel) {

		// Monographic component part
		case 'a':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_ARTICLE);
			break;
		// Serial component part
		case 'b':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_ISSUE);
			break;
		// Collection
		case 'c':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_COLLECTION);
			break;
		// Subunit
		case 'd':
			break;
		// Integrating resource
		case 'i':
			break;
		// Monograph/Item
		case 'm':
			model.add(rdfSubject, RDF.type, Constants.NS_BIBO_DOCUMENT);
			break;
		// Serial
		case 's':

			switch (data008pos21) {
			// # - None of the following
			case " ":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// d - Updating database
			case "d":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// l - Updating loose-leaf
			case "l":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// m - Monographic series
			case "m":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_SERIES);
				break;
			// n - Newspaper
			case "n":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// p - Periodical
			case "p":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// w - Updating Web site
			case "w":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;
			// | - No attempt to code
			case "|":
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;

			default:
				model.add(rdfSubject, RDF.type, Constants.NS_BIBO_PERIODICAL);
				break;

			}

			break;

		default:
			break;

		}

		switch (data007pos00) {

		// Map
		case "a":
			break;
		// Electronic resource
		case "c":
			switch (data007pos01) {
//			// a - Tape cartridge
//			case "a":
//				break;
//			// b - Chip cartridge
//			case "b":
//				break;
//			// c - Computer optical disc cartridge
//			case "c":
//				break;
//			// d - Computer disc, type unspecified
//			case "d":
//				break;
//			// e - Computer disc cartridge, type unspecified
//			case "e":
//				break;
//			// f - Tape cassette
//			case "f":
//				break;
//			// h - Tape reel
//			case "h":
//				break;
//			// j - Magnetic disk
//			case "j":
//				break;
//			// k - Computer card
//			case "k":
//				break;
//			// m - Magneto-optical disc
//			case "m":
//				break;
//			// o - Optical disc
//			case "o":
//				break;
			// r - Remote
			case "r":
				model.add(rdfSubject, RDF.type,
						Constants.NS_RDA_CARRIERTYPE_ONLINERESOURCE);
				break;
//			// u - Unspecified
//			case "u":
//				break;
//			// z - Other
//			case "z":
//				break;
//			// | - No attempt to code
//			case "|":
//				break;
			default:
				model.add(rdfSubject, RDF.type,
						Constants.NS_RDA_CARRIERTYPE_COMPUTER);
				break;

			}
			break;
		// Globe
		case "d":
			break;
		// Tactile material
		case "f":
			model.add(rdfSubject, RDF.type, Constants.NS_LIB_BRAILLE);
			break;
		// Projected graphic
		case "g":
			break;
		// Microform
		case "h":
			model.add(rdfSubject, RDF.type,
					Constants.NS_RDA_CARRIERTYPE_MICROFORM);
			break;
		// Nonprojected graphic
		case "k":
			break;
		// Motion picture
		case "m":
			break;
		// Kit
		case "o":
			model.add(rdfSubject, RDF.type, Constants.NS_LIB_BRAILLE);
			break;
		// Notated music
		case "q":
			break;
		// Remote-sensing image
		case "r":
			break;
		// Sound recording
		case "s":
			break;
		// Text
		case "t":
			break;
		// Videorecording
		case "v":
			break;
		// Unspecified
		case "z":
			break;
		}

	}

}
