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
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.RDF;

public class Title {

	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {

		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			String tag = df.getTag();
			String data = "";

			switch (tag) {

			case "245":

				String sfA = "";
				String sfB = "";
				String sfNorP = "";

				List<Subfield> listSubfields = df.getSubfields();
				for (Subfield sf : listSubfields) {
					// String s = sf.getData().replaceAll("( *)$", "$1");
					if (sf.getCode() == 'a') {
						sfA = sf.getData();
						sfA = StringTool.cleanUp(sfA);
						System.out.println(sfA);
					} else if (sf.getCode() == 'b') {
						sfB = sf.getData();
						sfB = StringTool.cleanUp(sfB);
					} else if ((sf.getCode() == 'n') || (sf.getCode() == 'p')) {
						sfNorP = sfNorP.concat(" ").concat(sf.getData());
						sfNorP = StringTool.cleanUp(sfNorP);
					}
				}
				// Triple bilden (ISBN13 ohne Bindestrich)
				rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
						+ id);
				model.add(rdfSubject, DC_11.title, sfA);

				// Triple bilden
				if (!sfB.equals("")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					rdfPredicate = model
							.createProperty(Constants.NS_RDA_OTHER_TITLE_INFORMATION);
					model.add(rdfSubject, rdfPredicate, sfB);
				}
				if (!sfNorP.equals("")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					rdfPredicate = model
							.createProperty(Constants.NS_RDA_OTHER_TITLE_INFORMATION);
					model.add(rdfSubject, rdfPredicate, sfNorP);
				}
				break;

			case "130":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.alternative, data);
				}
				break;

			case "240":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.alternative, data);
				}
				break;

			case "210":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					rdfPredicate = model
							.createProperty(Constants.NS_BIBO_SHORT_TITLE);
					model.add(rdfSubject, rdfPredicate, data);
				}
				break;

			case "246":
				int i2 = Character.getNumericValue(df.getIndicator2());
				if (i2 == 1) {
					if (df.getSubfield('a') != null) {
						data = df.getSubfield('a').getData();
						data = StringTool.cleanUp(data);
						rdfSubject = model
								.createResource(Constants.NS_HELVETICAT_BIB
										+ id);
						model.add(rdfSubject, DCTerms.alternative, data);
					}
				}
				break;

			}

		}

	}
}
