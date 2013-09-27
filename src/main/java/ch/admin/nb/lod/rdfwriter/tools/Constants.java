package ch.admin.nb.lod.rdfwriter.tools;

public class Constants {

	// Sonderzeichen
	public static final String NON_SORT_BEGIN = "\u0098";
	public static final String NON_SORT_END = "\u009c";
	public static final String NON_PRINTABLE_CHARACTERS = "\\p{Cntrl}";

	// Marc-Feld-Arrays
	public static final String[] F1XX = { "100", "110", "111", "130" };
	public static final String[] F6XX = { "600", "610", "611", "630", "648",
			"650", "651", "655" };
	public static final String[] F7XX = { "700", "710", "711", "730" };

	// DINI-AG-KIM
	public static final String[] KIM_TITLE = { "245", "130", "240", "210",
			"246" };
	public static final String[] KIM_CREATOR = { "100", "110", "111", "700",
			"710", "711" };

	// Dublin Core
	public static final String NS_DC_PREFIX = "dc";
	public static final String NS_DCTERMS_PREFIX = "dcterms";

	// Bibliographic Ontology (bibo)
	public static final String NS_BIBO = "http://purl.org/ontology/bibo/";
	public static final String NS_BIBO_PREFIX = "bibo";
	public static final String NS_BIBO_ISBN10 = "http://purl.org/ontology/bibo/isbn10";
	public static final String NS_BIBO_ISBN13 = "http://purl.org/ontology/bibo/isbn13";
	public static final String NS_BIBO_SHORT_TITLE = "http://purl.org/ontology/bibo/shortTitle";
	public static final String NS_BIBO_MAP = "http://purl.org/ontology/bibo/Map";
	public static final String NS_BIBO_AUDIOVISUAL = "http://purl.org/ontology/bibo/AudioVisualDocument";
	public static final String NS_BIBO_SERIES = "http://purl.org/ontology/bibo/Series";
	public static final String NS_BIBO_PERIODICAL = "http://purl.org/ontology/bibo/Periodical";
	public static final String NS_BIBO_ARTICLE = "http://purl.org/ontology/bibo/Article";
	public static final String NS_BIBO_ISSUE = "http://purl.org/ontology/bibo/Issue";

	// Helveticat
	// Titeldaten (helveticatBib)
	public static final String NS_HELVETICAT_BIB = "http://nb.admin.ch/lod/helveticat/bib";
	public static final String NS_HELVETICAT_BIB_PREFIX = "helveticatBib";
	// Normdaten (helveticatAuth)
	public static final String NS_HELVETICAT_AUTH = "http://nb.admin.ch/lod/helveticat/auth";
	public static final String NS_HELVETICAT_AUTH_PREFIX = "helveticatAuth";

	// NB Ontology (nbo)
	public static final String NS_NBO = "http://nb.admin.ch/lod/ontology#";
	public static final String NS_NBO_PREFIX = "nbo";
	public static final String NS_NBO_ISBN10 = "http://nb.admin.ch/lod/ontology#isbn10";
	public static final String NS_NBO_ISBN13 = "http://nb.admin.ch/lod/ontology#isbn13";
	public static final String NS_NBO_ISBN_INVALID = "http://nb.admin.ch/lod/ontology#isbnInvalid";
	public static final String NS_NBO_ISBN_INVALID_WITH_HYPHEN = "http://nb.admin.ch/lod/ontology#isbnInvalidWithHyphen";
	public static final String NS_NBO_SUBJECT_RSWK = "http://nb.admin.ch/lod/ontology#subjectRswk";

	// RDA
	public static final String NS_RDA = "http://rdvocab.info/Elements/";
	public static final String NS_RDA_PREFIX = "rda";
	public static final String NS_RDA_PUBLICATION_STATEMENT = "http://rdvocab.info/Elements/publicationStatement";
	public static final String NS_RDA_PLACE_OF_PUBLICATION = "http://rdvocab.info/Elements/placeOfPublication";
	public static final String NS_RDA_OTHER_TITLE_INFORMATION = "http://rdvocab.info/Elements/otherTitleInformation";

	// RDA Carriertype
	public static final String NS_RDA_CARRIERTYPE = "http://rdvocab.info/termList/RDACarrierType/";
	public static final String NS_RDA_CARRIERTYPE_PREFIX = "rdacarriertype";
	public static final String NS_RDA_CARRIERTYPE_UNMEDIATED = "http://rdvocab.info/termList/RDACarrierType/1044";
	public static final String NS_RDA_CARRIERTYPE_MICROFORM = "http://rdvocab.info/termList/RDAMediaType/1002";
	public static final String NS_RDA_CARRIERTYPE_ONLINERESOURCE = "http://rdvocab.info/termList/RDACarrierType/1018";
	public static final String NS_RDA_CARRIERTYPE_COMPUTER = "http://rdvocab.info/termList/RDACarrierType/1010";

	// GND
	public static final String NS_GND = "http://d-nb.info/gnd/";
	public static final String NS_GND_PREFIX = "gnd";

	// LIB
	public static final String NS_LIB = "http://purl.org/library/";
	public static final String NS_LIB_PREFIX = "lib";
	public static final String NS_LIB_BRAILLE = "http://purl.org/library/BrailleBook";
	
	// ISBD
	public static final String NS_ISBD = "http://iflastandards.info/ns/isbd/terms/mediatype/";
	public static final String NS_ISBD_PREFIX = "isbdmediatype";
	public static final String NS_ISBD_MULTIMEDIA = "http://iflastandards.info/ns/isbd/terms/mediatype/T1008";
	
	
}
