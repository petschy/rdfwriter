package eu.schwery.lod.marctordf;

import java.util.List;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC_11;

import eu.schwery.lod.tools.Constants;
import eu.schwery.lod.tools.StringTool;

public class Marc245 {

	public void toRdf(DataField df, Model model, String id) {

		// RDF-Triple
		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		String title = "";

		List<Subfield> listSubfields = df.getSubfields();

		String subfieldA = "";
		String subfieldData = "";
		for (Subfield sf : listSubfields) {

			// String s = sf.getData().replaceAll("( *)$", "$1");
			if (sf.getCode() == 'a') {
				subfieldA = sf.getData();
				subfieldA = subfieldA.replaceAll(" \\W$", "");
			} else {
//				subfieldData = subfieldData.concat(" ").concat(sf.getData());
			}
		}
		title = subfieldA.concat(subfieldData);
		
		title = StringTool.cleanUp(title);

		// Triple bilden (ISBN13 ohne Bindestrich)
		rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);
		model.add(rdfSubject, DC_11.title, title);

	}
}
