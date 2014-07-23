package ch.admin.nb.lod.rdfwriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.MarcStreamWriter;
import org.marc4j.MarcWriter;
import org.marc4j.marc.Record;

public class MarcFileSplitter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String mrcFileIn = "mrc/helv-bib-all.mrc";
		// String mrcFileIn =
		// "C:/Users/Peter/Documents/Werkstatt/Datenbereinigung_GND-NB/"
		// + "Marc-Dateien/2010_subj_perm_rec.mrc";
		// String txtFileOut =
		// "//adb.intra.admin.ch/userhome$/NB-01/U80714165/data/Documents/Projekt_GND/2014-01-22_AuthRecInError.txt";
		String mrcFileOut = "mrc/marcfileout";
		String mrcFileOutPart = "";
		String fileExtension = ".mrc";
		InputStream input = null;
		OutputStream output = null;
		// Tools tool = new Tools();
		int fileCounter = 1;
		try {
			input = new FileInputStream(mrcFileIn);
			mrcFileOutPart = mrcFileOut + fileCounter + fileExtension;
			output = new FileOutputStream(mrcFileOutPart);
			MarcReader reader;
			MarcWriter writer;
			reader = new MarcStreamReader(input);
			writer = new MarcStreamWriter(output, "UTF8");
			int recordCounter = 1;
			while (reader.hasNext()) {
				Record record = reader.next();
				// tool.writeFile(record.toString(), txtFileOut);
				if (recordCounter % 100000 == 0) {
					
					System.out.println("====================== Record no. "
							+ recordCounter + " FileNo: " + fileCounter);
					System.out.println(record.toString());

					fileCounter++;
					writer.close();
					mrcFileOutPart = mrcFileOut + fileCounter + fileExtension;
					output = new FileOutputStream(mrcFileOutPart);
					writer = new MarcStreamWriter(output, "UTF8");

					
				} else {
					System.out.println("====================== Record no. "
							+ recordCounter + " FileNo: " + fileCounter);
					System.out.println(record.toString());

					writer.write(record);

				}
				recordCounter++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
