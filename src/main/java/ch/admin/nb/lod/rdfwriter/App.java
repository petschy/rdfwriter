package ch.admin.nb.lod.rdfwriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.marctordf.Marc020;
import ch.admin.nb.lod.rdfwriter.marctordf.Marc1XX;
import ch.admin.nb.lod.rdfwriter.marctordf.Marc245;
import ch.admin.nb.lod.rdfwriter.marctordf.Marc260;
import ch.admin.nb.lod.rdfwriter.marctordf.Marc689;
import ch.admin.nb.lod.rdfwriter.marctordf.Marc6XX;
import ch.admin.nb.lod.rdfwriter.marctordf.Marc7XX;
import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.Variables;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;

/**
 * Hello world!
 * 
 */
public class App {

	private App() {

		// Listen und Felder
		List<VariableField> listVariableField;
		VariableField variableField;

		// Marc-Objekte
		Marc020 marc020 = new Marc020();
		Marc1XX marc1XX = new Marc1XX();
		Marc245 marc245 = new Marc245();
		Marc260 marc260 = new Marc260();
		Marc6XX marc6XX = new Marc6XX();
		Marc689 marc689 = new Marc689();
		Marc7XX marc7XX = new Marc7XX();

		// leeres Standard-Modell
		Model model = ModelFactory.createDefaultModel();
		
		// Variablen
		Variables var = new Variables();
		

		try {
			OutputStream rdfXmlOutput = new FileOutputStream(var.fileRdfXml);
			OutputStream rdfTurtleOutput = new FileOutputStream(var.fileRdfTurtle);
			InputStream input = new FileInputStream("mrc/testDollarT.mrc");
			MarcReader reader = new MarcStreamReader(input);
			while (reader.hasNext()) {
				Record record = reader.next();
				String bibId;
				bibId = record.getControlNumber();
				// Nur Datensätze mit gültiger BibId verarbeiten
				if ((bibId.length() == 13)
						&& bibId.substring(0, 4).equals("vtls")) {
					bibId = bibId.replace("vtls", "");
					// System.out.println(record.toString());

					// Feld 020: ISBN
					listVariableField = record.getVariableFields("020");
					marc020.toRdf(listVariableField, model, bibId);

					// Feld 1XX
					listVariableField = record.getVariableFields(Constants.F1XX);
					if (listVariableField.size() == 1){
						// Feld 1XX ist nicht repetierbar
						marc1XX.toRdf(listVariableField, model, bibId);
					} else {
						// TODO BibId in Datei schreiben
					}

					// Feld 245: Titel
					variableField = record.getVariableField("245");
					if (variableField != null) {
						marc245.toRdf((DataField) variableField, model, bibId);
					} else {
						// kein Feld 245 vorhanden

						// TODO BibId in Datei schreiben
					}
					
					// Feld 260: Titel
					listVariableField = record.getVariableFields("260");
					if (variableField != null) {
						marc260.toRdf(listVariableField, model, bibId);
					} else {
						// kein Feld 245 vorhanden

						// TODO BibId in Datei schreiben
					}

					// Feld 6XX
					listVariableField = record.getVariableFields(Constants.F6XX);
					if (listVariableField.size() != 0){
						marc6XX.toRdf(listVariableField, model, bibId);
					} 

					// Feld 689
					listVariableField = record.getVariableFields("689");
					if (listVariableField.size() != 0){
						marc689.toRdf(listVariableField, model, bibId);
					} 
					
					// Feld 7XX
					listVariableField = record.getVariableFields(Constants.F7XX);
					if (listVariableField.size() != 0){
						marc7XX.toRdf(listVariableField, model, bibId);
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
			model.setNsPrefix(Constants.NS_GND_PREFIX, Constants.NS_GND);
//			model.write(System.out);
			// RdfXml in Datei schreiben
			model.write(rdfXmlOutput);
			// Turtle in Datei schreiben
			model.write(rdfTurtleOutput, "TTL");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new App();
	}

}
