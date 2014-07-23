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
	public static final String[] KIM_TITLE = { "130", "210", "240","245",
			"246" };
	public static final String[] KIM_CREATOR = { "100", "110", "111", "700",
			"710", "711" };
	public static final String[] KIM_RELATION = { "770", "773", "774", "775", "776", "780", "785", "800", "810", "811", "830"};
	public static final String[] KIM_IDENTIFIER = { "020", "022", "024", "030", "035"};
	public static final String[] KIM_SERIES_STATEMENT = { "440", "490"};
	
	// Dublin Core
	public static final String NS_DC_PREFIX = "dc";
	public static final String NS_DCTERMS_PREFIX = "dcterms";

	// Bibliographic Ontology (bibo)
	public static final String NS_BIBO = "http://purl.org/ontology/bibo/";
	public static final String NS_BIBO_PREFIX = "bibo";
	public static final String NS_BIBO_ISBN = "http://purl.org/ontology/bibo/isbn";
	public static final String NS_BIBO_ISSN = "http://purl.org/ontology/bibo/issn";
	public static final String NS_BIBO_ISBN10 = "http://purl.org/ontology/bibo/isbn10";
	public static final String NS_BIBO_ISBN13 = "http://purl.org/ontology/bibo/isbn13";
	public static final String NS_BIBO_SHORT_TITLE = "http://purl.org/ontology/bibo/shortTitle";
	public static final String NS_BIBO_MAP = "http://purl.org/ontology/bibo/Map";
	public static final String NS_BIBO_AUDIOVISUAL = "http://purl.org/ontology/bibo/AudioVisualDocument";
	public static final String NS_BIBO_SERIES = "http://purl.org/ontology/bibo/Series";
	public static final String NS_BIBO_PERIODICAL = "http://purl.org/ontology/bibo/Periodical";
	public static final String NS_BIBO_ARTICLE = "http://purl.org/ontology/bibo/Article";
	public static final String NS_BIBO_ISSUE = "http://purl.org/ontology/bibo/Issue";
	public static final String NS_BIBO_EDITION = "http://purl.org/ontology/bibo/edition";
	public static final String NS_BIBO_CODEN = "http://purl.org/ontology/bibo/coden";
	public static final String NS_BIBO_COLLECTION = "http://purl.org/ontology/bibo/Collection";
	public static final String NS_BIBO_DOCUMENT = "http://purl.org/ontology/bibo/Document";
	public static final String NS_BIBO_AUDIODOCUMENT = "http://purl.org/ontology/bibo/AudioDocument";
	public static final String NS_BIBO_IMAGE = "http://purl.org/ontology/bibo/Image";

	// Helveticat
	// Titeldaten (helveticatBib)
	public static final String NS_HELVETICAT_BIB = "http://permalink/snl.ch/bib/";
	public static final String NS_HELVETICAT_BIB_PREFIX = "helveticatBib";
	// Normdaten (helveticatAuth)
	public static final String NS_HELVETICAT_AUTH = "http://permalink/snl.ch/auth/";
	public static final String NS_HELVETICAT_AUTH_PREFIX = "helveticatAuth";

	// NB Ontology (nbo)
	public static final String NS_NBO = "http://nb.admin.ch/lod/ontology#";
	public static final String NS_NBO_PREFIX = "nbo";
	public static final String NS_NBO_ISBN10 = "http://nb.admin.ch/lod/ontology#isbn10";
	public static final String NS_NBO_ISBN13 = "http://nb.admin.ch/lod/ontology#isbn13";
	public static final String NS_NBO_ISBN_INVALID = "http://nb.admin.ch/lod/ontology#isbnInvalid";
	public static final String NS_NBO_ISBN_INVALID_WITH_HYPHEN = "http://nb.admin.ch/lod/ontology#isbnInvalidWithHyphen";
	public static final String NS_NBO_SUBJECT_RSWK = "http://nb.admin.ch/lod/ontology#subjectRswk";
	public static final String NS_NBO_HELVETICAT_AUTH_ID = "http://nb.admin.ch/lod/ontology#helveticatAuthId";
	public static final String NS_NBO_HELVETICAT_PERSON = "http://nb.admin.ch/lod/ontology#preferredNameForThePerson";
	public static final String NS_NBO_HELVETICAT_CORPORATE_BODY = "http://nb.admin.ch/lod/ontology#preferredNameForTheCorporateBody";
	public static final String NS_NBO_HELVETICAT_CONFERENCE = "http://nb.admin.ch/lod/ontology#preferredNameForTheConferenceOrEvent";
	public static final String NS_NBO_HELVETICAT_WORK = "http://nb.admin.ch/lod/ontology#preferredNameForTheWork";

	// RDA
	// Elements
	public static final String NS_RDA = "http://rdvocab.info/Elements/";
	public static final String NS_RDA_PREFIX = "rda";
	public static final String NS_RDA_PUBLICATION_STATEMENT = "http://rdvocab.info/Elements/publicationStatement";
	public static final String NS_RDA_PLACE_OF_PUBLICATION = "http://rdvocab.info/Elements/placeOfPublication";
	public static final String NS_RDA_OTHER_TITLE_INFORMATION = "http://rdvocab.info/Elements/otherTitleInformation";
	// RDARelationshipsWEMI
	public static final String NS_RDA_WEMI = "http://rdvocab.info/RDARelationshipsWEMI/";
	public static final String NS_RDA_WEMI_PREFIX = "rdaRelationshipsWEMI";
	public static final String NS_RDA_WEMI_PRECEDED_BY = "http://rdvocab.info/RDARelationshipsWEMI/precededBy";
	public static final String NS_RDA_WEMI_SUCCEEDED_BY = "http://rdvocab.info/RDARelationshipsWEMI/succeededBy";
	// Carriertype
	public static final String NS_RDA_CARRIERTYPE = "http://rdvocab.info/termList/RDACarrierType/";
	public static final String NS_RDA_CARRIERTYPE_PREFIX = "rdaCarriertype";
	public static final String NS_RDA_CARRIERTYPE_UNMEDIATED = "http://rdvocab.info/termList/RDACarrierType/1044";
	public static final String NS_RDA_CARRIERTYPE_MICROFORM = "http://rdvocab.info/termList/RDAMediaType/1002";
	public static final String NS_RDA_CARRIERTYPE_ONLINERESOURCE = "http://rdvocab.info/termList/RDACarrierType/1018";
	public static final String NS_RDA_CARRIERTYPE_COMPUTER = "http://rdvocab.info/termList/RDACarrierType/1010";
	// Elements/u
	public static final String NS_RDAU = "http://rdaregistry.info/Elements/u/";
	public static final String NS_RDAU_PREFIX = "rdau";
	public static final String NS_RDAU_PREFIX_OTHER_TITLE_INFORMATION = "http://rdaregistry.info/Elements/u/P60493";
	public static final String NS_RDAU_PREFIX_PUBLICATION_STATEMENT = "http://rdaregistry.info/Elements/u/P60333";
	public static final String NS_RDAU_PREFIX_PLACE_OF_PUBLICATION = "http://rdaregistry.info/Elements/u/P60163";
	public static final String NS_RDAU_PREFIX_PRECEDED_BY = "http://rdaregistry.info/Elements/u/P60261";
	public static final String NS_RDAU_PREFIX_SUCCEEDED_BY = "http://rdaregistry.info/Elements/u/P60278";
	// Mediatype
	public static final String NS_RDA_MEDIATYPE = "http://rdvocab.info/termList/RDAMediaType/";
	public static final String NS_RDA_MEDIATYPE_PREFIX = "rdaMediatype";
	public static final String NS_RDA_MEDIATYPE_AUDIO = "http://rdvocab.info/termList/RDAMediaType/1001";


	// GND
	public static final String NS_GND = "http://d-nb.info/gnd/";
	public static final String NS_GND_PREFIX = "gnd";

	// LIB
	public static final String NS_LIB = "http://purl.org/library/";
	public static final String NS_LIB_PREFIX = "lib";
	public static final String NS_LIB_BRAILLE = "http://purl.org/library/BrailleBook";
	
	// ISBD
	// Mediatype
	public static final String NS_ISBD_MEDIATYPE = "http://iflastandards.info/ns/isbd/terms/mediatype/";
	public static final String NS_ISBD_MEDIATYPE_PREFIX = "isbdMediatype";
	public static final String NS_ISBD_MEDIATYPE_MULTIMEDIA = "http://iflastandards.info/ns/isbd/terms/mediatype/T1008";
	// Elements
	public static final String NS_ISBD_ELEMENTS = "http://iflastandards.info/ns/isbd/elements/";
	public static final String NS_ISBD_ELEMENTS_PREFIX = "isbd";
	public static final String NS_ISBD_ELEMENTS_HASEXTENT = "http://iflastandards.info/ns/isbd/elements/P1053";
	
	// LOC
	public static final String NS_MARC_LANGUAGES = "http://id.loc.gov/vocabulary/languages/";
	
	// Umbel
	public static final String NS_UMBEL = "http://umbel.org/umbel#";
	public static final String NS_UMBEL_PREFIX = "umbel";
	public static final String NS_UMBEL_ISLIKE = "http://umbel.org/umbel#isLike";
	
	// DOI
	public static final String NS_DOI = "http://dx.doi.org/";
		
	// URN
	public static final String NS_URN = "http://nbn-resolving.de/";
	
	
	
}
