package eu.schwery.lod.marctordf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;

import eu.schwery.lod.tools.Constants;
import eu.schwery.lod.tools.StringTool;

public class Marc6XX {
	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {

		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			// RDF-Triple
			Resource rdfSubject;
			Property rdfPredicate;
			Resource rdfObject;

			String subject = "";

			List<Subfield> listSubfields = df.getSubfields();

			String subfieldA = "";
			String subfieldData = "";
			String dcType = "";

			if (df.getSubfield('0') != null) {
				subject = df.getSubfield('0').getData();
				if (subject.startsWith("(DE-588)")) {
					// subject.replaceAll(Pattern.quote("(DE-588)"),
					// Matcher.quoteReplacement(""));
					subject = subject.substring(8);
					dcType = "dcterms";
					rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
							+ id);
					rdfObject = model
							.createResource(Constants.NS_GND + subject);
					model.add(rdfSubject, DCTerms.subject, rdfObject);
					System.out.println(subject);
				}
			} else {
				for (Subfield sf : listSubfields) {
					if (sf.getCode() == 'a') {
						subfieldA = sf.getData();
					} else {
						if (sf.getCode() != '2')
							subfieldData = subfieldData.concat(" ").concat(
									sf.getData());
					}
				}
				dcType = "dc";

			}
			subject = subfieldA.concat(subfieldData);

			subject = StringTool.cleanUp(subject);
			System.out.println(subject);

			// Triple bilden
			if (!subject.equals("")) {
				if (dcType.equals("dc")) {
					rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB
							+ id);
					model.add(rdfSubject, DC_11.subject, subject);
				} else {
					if (dcType.equals("dcterms")) {
						rdfSubject = model
								.createResource(Constants.NS_HELVETICAT_BIB + id);
						model.add(rdfSubject, DCTerms.subject, subject);

					}
				}
			}

		}

	}

}
