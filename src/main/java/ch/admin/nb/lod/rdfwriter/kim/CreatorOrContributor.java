package ch.admin.nb.lod.rdfwriter.kim;

import java.util.List;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;

/**
 * <h4>Personen und Körperschaften</h4>
 * <p>Eine SPARQL-Query gleicht die Ansetzung aus den Marc21-Felder 100, 110, 111, 700, 710 und 711 mit NB-Autoritäten im RDF-Model ab.
 *  Existiert ein entsprechender NB-Autoritätsdatensatz, wird die Auth-Id als Verknüpfung ausgegeben (dcterms),
 *  sonst die Ansetzung als String (dc)</p>
 *  
* @author Peter Schwery
 *  
  *
 */
public class CreatorOrContributor {

	/**
	 * @param listVariableField Marc21-Felder: 100, 110, 111, 700, 710, 711
	 * @param model 			RDF-Model
	 * @param id 				Bib-Id
	 */
	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {

		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			String tag = df.getTag();
			String data = "";
			String p;
			List<Subfield> listSubfields = df.getSubfields();

			switch (tag) {

			case "100":

				for (Subfield sf : listSubfields) {
					if (sf.getCode() == 'a') {
						data = sf.getData();
					} else {
						data = data.concat(" ").concat(sf.getData());
					}
				}
				data = StringTool.cleanUp(data);

				data = queryModel(data, "preferredNameForThePerson", model);

				if (data.startsWith("http://nb.admin.ch")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.creator, data);

				} else {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.creator, data);

				}

				break;

			case "110":
				for (Subfield sf : listSubfields) {
					if (sf.getCode() == 'a') {
						data = sf.getData();
					} else {
						data = data.concat(" ").concat(sf.getData());
					}
				}
				data = StringTool.cleanUp(data);

				data = queryModel(data, "preferredNameForTheCorporateBody", model);

				if (data.startsWith("http://nb.admin.ch")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.creator, data);

				} else {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.creator, data);

				}

				break;

			case "111":
				for (Subfield sf : listSubfields) {
					if (sf.getCode() == 'a') {
						data = sf.getData();
					} else {
						data = data.concat(" ").concat(sf.getData());
					}
				}
				data = StringTool.cleanUp(data);

				data = queryModel(data, "preferredNameForTheConferenceOrEvent", model);

				if (data.startsWith("http://nb.admin.ch")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.creator, data);

				} else {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.creator, data);

				}

				break;

			case "700":
				for (Subfield sf : listSubfields) {
					if (sf.getCode() == 'a') {
						data = sf.getData();
					} else {
						data = data.concat(" ").concat(sf.getData());
					}
				}
				data = StringTool.cleanUp(data);

				data = queryModel(data, "preferredNameForThePerson", model);

				if (data.startsWith("http://nb.admin.ch")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.contributor, data);

				} else {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.contributor, data);

				}


				break;

			case "710":
				for (Subfield sf : listSubfields) {
					if (sf.getCode() == 'a') {
						data = sf.getData();
					} else {
						data = data.concat(" ").concat(sf.getData());
					}
				}
				data = StringTool.cleanUp(data);


				data = queryModel(data, "preferredNameForTheCorporateBody", model);

				if (data.startsWith("http://nb.admin.ch")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.contributor, data);

				} else {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.contributor, data);

				}

				break;

			case "711":
				for (Subfield sf : listSubfields) {
					if (sf.getCode() == 'a') {
						data = sf.getData();
					} else {
						data = data.concat(" ").concat(sf.getData());
					}
				}
				data = StringTool.cleanUp(data);

				data = queryModel(data, "preferredNameForTheConferenceOrEvent", model);

				if (data.startsWith("http://nb.admin.ch")) {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DCTerms.contributor, data);

				} else {
					rdfSubject = model
							.createResource(Constants.NS_HELVETICAT_BIB + id);
					model.add(rdfSubject, DC_11.contributor, data);

				}

				break;

			}
		}
	}

	private String queryModel(String data, String arg, Model model) {
		
		// Anführungszeichen in Ansetzung löschen (-> Java-Exception)
		data = data.replaceAll("\"", "");
		

		String queryString = "PREFIX nbo" + ": <"
				+ "http://nb.admin.ch/lod/ontology#" + "> \n"
				+ "SELECT ?helveticatAuthId \n" + "WHERE { \n"
				+ "?helveticatAuthId \n" + "nbo:" + arg + " \n" + "\"" + data
				+ "\"" + " .\n} ";

//		 System.out.println(queryString);

		Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
//		 ResultSetFormatter.out(System.out, results, query);

		while (results.hasNext()) {
			QuerySolution soln = results.next();
			Resource resource = soln.getResource("helveticatAuthId");
			data = resource.toString();
		}

		qe.close();

//		System.out.println("Data: " + data);

		return data;

	}
}
