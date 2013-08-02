package eu.schwery.lod.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {
	
	public void write(String s, String fileName){
		File file = new File(fileName);
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(s);
			fw.write(System.getProperty("line.separator"));
			fw.flush();
			fw.close();
				
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
