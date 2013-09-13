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
		// Leerzeichen Doppelpunkt oder Schrägstrich am Schluss löschen
		s = s.replaceAll(" :$", "");
		s = s.replaceAll(" /$", "");
		// Normalisieren NFC
		s = Normalizer.normalize(s, Normalizer.Form.NFC);
		return s;
		
	}
}
