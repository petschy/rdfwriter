package ch.admin.nb.lod.rdfwriter.marctordf;

import java.util.List;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC_11;

public class Marc260 {

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
			String subfieldData = "";
			for (Subfield sf : listSubfields) {
				if (sf.getCode() == 'a') {
					subfieldA = sf.getData();
				} else {
					subfieldData = subfieldData.concat(" ")
							.concat(sf.getData());
				}

			}
			publicationStatement = subfieldA.concat(subfieldData);

			publicationStatement = StringTool.cleanUp(publicationStatement);
			// Punkt nach Publikationsjahr löschen
			publicationStatement = publicationStatement.replaceAll("(\\d)\\.$", "$1");

			// Triple bilden
			if (!publicationStatement.equals("")) {
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);
				rdfPredicate = model
						.createProperty(Constants.NS_RDA_PUBLICATION_STATEMENT);
				model.add(rdfSubject, rdfPredicate, publicationStatement);
			}

		}

	}

}
