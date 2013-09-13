package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.DataField;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;

public class CreatorOrContributor {

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
			
			case "100":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.creator, data);
				}
				break;

			case "110":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.creator, data);
				}
				break;

			case "111":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.creator, data);
				}
				break;
				
			case "700":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.contributor, data);
				}
				break;

			case "710":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.contributor, data);
				}
				break;

			case "711":
				if (df.getSubfield('a') != null) {
					data = df.getSubfield('a').getData();
					data = StringTool.cleanUp(data);
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.contributor, data);
				}
				break;


			}
		}
	}
}
