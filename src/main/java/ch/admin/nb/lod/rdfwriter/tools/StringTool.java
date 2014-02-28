package ch.admin.nb.lod.rdfwriter.tools;

import java.text.Normalizer;

public class StringTool {

	public static String cleanUp(String s){
		
		// Nicht druckbare Zeichen durch Leerzeichen ersetzen
		s = s.replaceAll(Constants.NON_PRINTABLE_CHARACTERS, " ");
		// Nichtsortierzeichen löschen
		s = s.replaceAll(Constants.NON_SORT_BEGIN, "");
		s = s.replaceAll(Constants.NON_SORT_END, "");
		// Mehrfache Leerzeichen durch eines ersetzen
		s = s.replaceAll(" {2,}", " ");
		// ISBD-Interpunktion am Zeilenende löschen
		s = s.replaceAll(" :$", "");
		s = s.replaceAll(" ;$", "");
		s = s.replaceAll(" /$", "");
		s = s.replaceAll(" =$", "");
		s = s.replaceAll(",$", "");
		// Leerschlag an Zeilenanfang löschebn
		s = s.replaceAll("^ ", "");
		// Normalisieren NFC
		s = Normalizer.normalize(s, Normalizer.Form.NFC);
		return s;
		
	}
	
	/**
	 * @param str
	 * @param length
	 * @param c
	 * @return
	 */
	public static String rightPad(String str, Integer length, char c){
		return str + String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(c));
		
	}
	/**
	 * @param str
	 * @param length
	 * @param c
	 * @return
	 */
	public static String leftPad(String str, Integer length, char c){
		return String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(c)) + str;
		
	}
}

