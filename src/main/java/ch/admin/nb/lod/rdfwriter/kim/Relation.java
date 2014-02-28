package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.marc4j.marc.DataField;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;

/**
 * <h4>Relationen</h4>
 * <p>Unterfeld $w aus den Verknüpfungs-Feldern 77X und 78X und den Gesamttitel-Feldern 8XX wird als URI ausgegeben.</p>
 *  
* @author Peter Schwery
 *  
  *
 */
public class Relation {

	/**
	 * @param listVariableField	Marc21-Felder 770, 773, 774, 775, 776, 780, 785, 800, 810, 811, 830
	 * @param model 			RDF-Model
	 * @param id 				Bib-Id
	 */
	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {
		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);

		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			String tag = df.getTag();
			String data = "";

			if (df.getSubfield('w') != null) {

				switch (tag) {

				case "773":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					model.add(rdfSubject, DCTerms.isPartOf,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "774":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					model.add(rdfSubject, DCTerms.hasPart,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "775":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					
					model.add(rdfSubject, DCTerms.hasVersion,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "776":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					model.add(rdfSubject, DCTerms.isFormatOf,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "770":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					model.add(rdfSubject, DCTerms.isPartOf,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "780":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					rdfPredicate = model
							.createProperty(Constants.NS_RDA_WEMI_PRECEDED_BY);

					model.add(rdfSubject, rdfPredicate,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "785":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					rdfPredicate = model
							.createProperty(Constants.NS_RDA_WEMI_SUCCEEDED_BY);

					model.add(rdfSubject, rdfPredicate,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "800":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					model.add(rdfSubject, DCTerms.isPartOf,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "810":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					model.add(rdfSubject, DCTerms.isPartOf,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "811":
					data = df.getSubfield('w').getData();
					data = StringTool.leftPad(data, 9, '0');
					model.add(rdfSubject, DCTerms.isPartOf,
							Constants.NS_HELVETICAT_BIB + data);

					break;

				case "830":
						data = df.getSubfield('w').getData();
						data = StringTool.leftPad(data, 9, '0');
						model.add(rdfSubject, DCTerms.isPartOf,
								Constants.NS_HELVETICAT_BIB + data);
					break;

				}
			}
		}

	}

}
