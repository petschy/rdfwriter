package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;

/**
 * <h4>Gesamttitelangabe</h4>
 * <p>
 * Die Gesamttitelangabe aus Marc21-Feld 490 wird als
 * dcterms:bibliographicCitation ausgegeben.
 * </p>
 * 
 * @author Peter Schwery
 * 
 * 
 */
public class SeriesStatement {

	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {

		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);

		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			List<Subfield> listSubfields = df.getSubfields();
			String data = "";

			for (Subfield sf : listSubfields) {
				char code = sf.getCode();
				
				switch (code){
				case 'a':
					data = data.concat(" ").concat(sf.getData());
					break;
					
				case 'x':
					data = data.concat(", ISSN ").concat(sf.getData());
					break;
				case 'v':
					data = data.concat(" ").concat("; ").concat(sf.getData());
					break;
					
				}
				
				data = StringTool.cleanUp(data);
			}
//			System.out.println(data);
			model.add(rdfSubject, DCTerms.bibliographicCitation, data);

		}

	}
}
