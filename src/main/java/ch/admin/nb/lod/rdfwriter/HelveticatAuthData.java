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
import org.marc4j.marc.DataField;
import org.marc4j.marc.Leader;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC_11;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;
import ch.admin.nb.lod.rdfwriter.tools.Variables;

public class HelveticatAuthData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HelveticatAuthData();
	}

	public void parseHelveticatAuthData(Model model) {

		// Variablen
		Variables var = new Variables();

		try (InputStream input = new FileInputStream(var.fileMrcAuthIn);) {
			int recordCounter = 1;
			MarcReader reader = new MarcStreamReader(input);
			while (reader.hasNext()) {
				Record record = reader.next();
//				System.out.println(record);

				String id;
				id = record.getControlNumber();
				System.out.println("Auth-Record no. " + recordCounter + ": " + id);
				// Nur Datensätze mit gültiger BibId verarbeiten
				if ((id.length() == 13) && id.substring(0, 4).equals("vtls")) {
					id = id.replace("vtls", "sz");
					// Marc-Felder-Liste
					List<VariableField> listField1XX;
					List<VariableField> listField999;

					listField999 = record.getVariableFields("999");

					if (listField999.size() > 0) {
						for (VariableField vf : listField999) {
							DataField df = (DataField) vf;
							String s = df.getSubfield('a').getData();
							if (!s.startsWith("VIRTUA2")) {

//								System.out.println(id + "\t" + s);

								Resource rdfSubject;
								Property rdfPredicate;
								Resource rdfObject;

								String data = "";
								String tag = "";

								listField1XX = record
										.getVariableFields(Constants.F1XX);

								if (listField1XX.size() == 1) {

									tag = listField1XX.get(0).getTag();
									DataField df1xx = (DataField) listField1XX
											.get(0);
									List<Subfield> listSubfields = df1xx
											.getSubfields();
									switch (tag) {

									case "100":

										for (Subfield sf : listSubfields) {
											if (sf.getCode() == 'a') {
												data = sf.getData();
											} else {
												data = data.concat(" ").concat(
														sf.getData());
											}
										}
										data = StringTool.cleanUp(data);
										rdfSubject = model
												.createResource(Constants.NS_HELVETICAT_AUTH
														+ id);
										rdfPredicate = model
												.createProperty(Constants.NS_NBO_HELVETICAT_PERSON);
										model.add(rdfSubject, rdfPredicate,
												data);

										break;

									case "110":

										for (Subfield sf : listSubfields) {
											if (sf.getCode() == 'a') {
												data = sf.getData();
											} else {
												data = data.concat(" ").concat(
														sf.getData());
											}
										}
										data = StringTool.cleanUp(data);
										rdfSubject = model
												.createResource(Constants.NS_HELVETICAT_AUTH
														+ id);
										rdfPredicate = model
												.createProperty(Constants.NS_NBO_HELVETICAT_CORPORATE_BODY);
										model.add(rdfSubject, rdfPredicate,
												data);

										break;

									case "111":

										for (Subfield sf : listSubfields) {
											if (sf.getCode() == 'a') {
												data = sf.getData();
											} else {
												data = data.concat(" ").concat(
														sf.getData());
											}
										}
										data = StringTool.cleanUp(data);
										rdfSubject = model
												.createResource(Constants.NS_HELVETICAT_AUTH
														+ id);
										rdfPredicate = model
												.createProperty(Constants.NS_NBO_HELVETICAT_CONFERENCE);
										model.add(rdfSubject, rdfPredicate,
												data);

										break;

									case "130":

										for (Subfield sf : listSubfields) {
											if (sf.getCode() == 'a') {
												data = sf.getData();
											} else {
												data = data.concat(" ").concat(
														sf.getData());
											}
										}
										data = StringTool.cleanUp(data);
										rdfSubject = model
												.createResource(Constants.NS_HELVETICAT_AUTH
														+ id);
										rdfPredicate = model
												.createProperty(Constants.NS_NBO_HELVETICAT_WORK);
										model.add(rdfSubject, rdfPredicate,
												data);

										break;

									}

								}

								rdfSubject = model
										.createResource(Constants.NS_HELVETICAT_AUTH
												+ id);
								rdfPredicate = model
										.createProperty(Constants.NS_NBO_HELVETICAT_AUTH_ID);
								model.add(rdfSubject, rdfPredicate, id);

							}
						}
					}

				} else {
					// Ungültige BibId
					System.out.println("Invalid AuthId: ".concat(id));

					// TODO Ungültige BibId in Datei schreiben

				}
				recordCounter++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
