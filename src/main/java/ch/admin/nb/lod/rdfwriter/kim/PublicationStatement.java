package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;

/**
 * <h4>Orts-, Verlags- und Datumsangaben</h4>
 * <p>Wird eine Publikation von mehreren Verlagen an unterschiedlichen Verlagsorten herausgegeben, 
 * ist eine Zuordnung, welche Verlags- und Ortsangaben zueinander gehören, nicht mehr möglich.
 * Um Unschärfen im Falle von mehreren Verlags- und Ortsangaben zu vermeiden, wird zusätzlich ein Publication Statement
 * nach ISBD ausgegeben.</p>
 * 
 * @author Peter Schwery
 *
 */
public class PublicationStatement {

	/**
	 * @param listVariableField Marc21-Feld 260
	 * @param model 			RDF-Model
	 * @param id 				Bib-Id
	 */
	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {
		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			// RDF-Triple
			Resource rdfSubject;
			Property rdfPredicate;
			Resource rdfObject;

			String publicationStatement = "";

			List<Subfield> listSubfields = df.getSubfields();

			String subfieldA = "";
			String subfieldB = "";
			String subfieldC = "";

			String subfieldData = "";
			for (Subfield sf : listSubfields) {
				if (sf.getCode() == 'a') {
					subfieldA = sf.getData();
				} else if (sf.getCode() == 'b') {
					subfieldB = sf.getData();
					subfieldData = subfieldData.concat(" ")
							.concat(sf.getData());
				} else if (sf.getCode() == 'c') {
					subfieldC = sf.getData();
					subfieldData = subfieldData.concat(" ")
							.concat(sf.getData());
				}

			}
			publicationStatement = subfieldA.concat(subfieldData);

			publicationStatement = StringTool.cleanUp(publicationStatement);
			// Punkt nach Publikationsjahr löschen
			publicationStatement = publicationStatement.replaceAll("(\\d)\\.$",
					"$1");

			// Triple bilden
			if (!publicationStatement.equals("")) {
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				rdfPredicate = model
						.createProperty(Constants.NS_RDAU_PREFIX_PUBLICATION_STATEMENT);
				model.add(rdfSubject, rdfPredicate, publicationStatement);
			}

			if (!subfieldA.equals("")) {
				subfieldA = StringTool.cleanUp(subfieldA);
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				rdfPredicate = model
						.createProperty(Constants.NS_RDAU_PREFIX_PLACE_OF_PUBLICATION);
				model.add(rdfSubject, rdfPredicate, subfieldA);
			}
			
			if (!subfieldB.equals("")) {
				subfieldB = StringTool.cleanUp(subfieldB);
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				model.add(rdfSubject, DC_11.publisher, subfieldB);
			}

			if (!subfieldC.equals("")) {
				subfieldC = StringTool.cleanUp(subfieldC);
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				model.add(rdfSubject, DCTerms.issued, subfieldC);
			}


		}

	}
}
