package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.DataField;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * <h4>Ausgabebezeichnung</h4>
 * <p>Die Ausgabebezeichnung aus Marc21-Feld 250 $a wird als bibo:edition ausgegeben.</p>
 *  
* @author Peter Schwery
 *  
  *
 */
public class Edition {

	/**
	 * @param listVariableField Marc21-Feld 250
	 * @param model 			RDF-Model
	 * @param id 				Bib-Id
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

			if (df.getSubfield('a') != null) {
				
				data = df.getSubfield('a').getData();
				data = StringTool.cleanUp(data);
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				rdfPredicate = model
						.createProperty(Constants.NS_BIBO_EDITION);
				model.add(rdfSubject, rdfPredicate, data);

			}
				
			}

		
	}

}
