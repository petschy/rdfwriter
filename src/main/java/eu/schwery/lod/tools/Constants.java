package eu.schwery.lod.tools;

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

	// Dublin Core
	public static final String NS_DC_PREFIX = "dc";
	public static final String NS_DCTERMS_PREFIX = "dcterms";

	// Bibliographic Ontology (bibo)
	public static final String NS_BIBO = "http://purl.org/ontology/bibo/";
	public static final String NS_BIBO_PREFIX = "bibo";
	public static final String NS_BIBO_ISBN10 = "http://purl.org/ontology/bibo/isbn10";
	public static final String NS_BIBO_ISBN13 = "http://purl.org/ontology/bibo/isbn13";

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

	// GND
	public static final String NS_GND = "http://d-nb.info/gnd/";
	public static final String NS_GND_PREFIX = "gnd";

}
