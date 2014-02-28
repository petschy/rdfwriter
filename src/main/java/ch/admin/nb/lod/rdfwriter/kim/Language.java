package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;

/**
 * <h4>Sprache</h4>
 * <p>
 * Die Sprache wird aus Marc21-Feld 008/35-37 und aus Feld 041 $a (R) als
 * dcterms:language ausgegeben. Die Sprachencodes sind MARC Language Codes
 * (http://id.loc.gov/vocabulary/languages/)
 * </p>
 * 
 * @author Peter Schwery
 * 
 * 
 */
public class Language {

	/**
	 * @param cf
	 *            Marc21-Feld 008
	 * @param listVariableField
	 *            Marc21-Feld 041
	 * @param model
	 *            RDF-Model
	 * @param id
	 *            Bib-Id
	 */
	public void toRdf(ControlField cf, List<VariableField> listVariableField,
			Model model, String id) {
		
		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;


		String language = cf.getData();
		language = language.substring(35, 38);
		rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
				+ id);

		model.add(rdfSubject, DCTerms.language, Constants.NS_MARC_LANGUAGES.concat(language));


		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			List<Subfield> listSubfields = df.getSubfields();
			
			for (Subfield sf : listSubfields){
				if (sf.getCode() == 'a'){
					language = sf.getData();
					model.add(rdfSubject, DCTerms.language, Constants.NS_MARC_LANGUAGES.concat(language));
				}
			}
			
			

		}
	}

}
