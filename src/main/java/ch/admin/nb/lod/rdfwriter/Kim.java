package ch.admin.nb.lod.rdfwriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.Leader;
import org.marc4j.marc.Record;
import org.marc4j.marc.VariableField;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;

import ch.admin.nb.lod.rdfwriter.kim.CreatorOrContributor;
import ch.admin.nb.lod.rdfwriter.kim.MediaType;
import ch.admin.nb.lod.rdfwriter.kim.PublicationStatement;
import ch.admin.nb.lod.rdfwriter.kim.Title;
import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.Variables;

public class Kim {

	private Kim() {

		// leeres Standard-Modell
		Model model = ModelFactory.createDefaultModel();

		// Variablen
		Variables var = new Variables();
		
		HelveticatAuthData authData = new HelveticatAuthData();
		authData.parseHelveticatAuthData(model);

		// Marc-Felder-Liste
		List<VariableField> listVariableField;

		Title title = new Title();
		CreatorOrContributor creator = new CreatorOrContributor();
		PublicationStatement publicationStatement = new PublicationStatement();
		MediaType mediaType = new MediaType();

		try (OutputStream rdfXmlOutput = new FileOutputStream(var.fileRdfXml);
				OutputStream rdfTurtleOutput = new FileOutputStream(
						var.fileRdfTurtle);
				InputStream input = new FileInputStream(var.fileMrcIn);) {

			MarcReader reader = new MarcStreamReader(input);
			while (reader.hasNext()) {
				Record record = reader.next();
				String bibId;
				bibId = record.getControlNumber();
				// Nur Datensätze mit gültiger BibId verarbeiten
				if ((bibId.length() == 13)
						&& bibId.substring(0, 4).equals("vtls")) {
					bibId = bibId.replace("vtls", "");

					// Titel
					listVariableField = record
							.getVariableFields(Constants.KIM_TITLE);
					title.toRdf(listVariableField, model, bibId);

					// Personen und Körperschaften
					listVariableField = record
							.getVariableFields(Constants.KIM_CREATOR);
					creator.toRdf(listVariableField, model, bibId);

					// Orts-, Verlags- und Datumsangaben
					listVariableField = record.getVariableFields("260");
					publicationStatement.toRdf(listVariableField, model, bibId);

					// Medientypen
					Leader leader = record.getLeader();
					ControlField f007 = (ControlField) record
							.getVariableField("007");
					ControlField f008 = (ControlField) record
							.getVariableField("008");
					if (f008 != null) {
						if (f007 != null) {
							mediaType.toRdf(leader, f007, f008, model, bibId);
						} else {
							mediaType.toRdf(leader, f008, model, bibId);
						}
					} else {
						// TODO: Bib-Id in Error-Log: Kein Feld 008
					}

				} else {
					// Ungültige BibId
					System.out.println("Invalid BibId: ".concat(bibId));

					// TODO Ungültige BibId in Datei schreiben

				}
			}
			model.setNsPrefix(Constants.NS_HELVETICAT_BIB_PREFIX,
					Constants.NS_HELVETICAT_BIB);
			model.setNsPrefix(Constants.NS_BIBO_PREFIX, Constants.NS_BIBO);
			model.setNsPrefix(Constants.NS_NBO_PREFIX, Constants.NS_NBO);
			model.setNsPrefix(Constants.NS_DC_PREFIX, DC_11.getURI());
			model.setNsPrefix(Constants.NS_DCTERMS_PREFIX, DCTerms.getURI());
			model.setNsPrefix(Constants.NS_RDA_PREFIX, Constants.NS_RDA);
			model.setNsPrefix(Constants.NS_RDA_CARRIERTYPE_PREFIX, Constants.NS_RDA_CARRIERTYPE);
			model.setNsPrefix(Constants.NS_HELVETICAT_AUTH_PREFIX,
					Constants.NS_HELVETICAT_AUTH);
			// model.setNsPrefix(Constants.NS_GND_PREFIX, Constants.NS_GND);
			// model.write(System.out);
			// RdfXml in Datei schreiben
			model.write(rdfXmlOutput);
			// Turtle in Datei schreiben
			model.write(rdfTurtleOutput, "TTL");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Kim();
	}

}
