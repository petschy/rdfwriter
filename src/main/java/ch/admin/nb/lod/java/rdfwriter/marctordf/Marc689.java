package ch.admin.nb.lod.java.rdfwriter.marctordf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;

import ch.admin.nb.lod.rdfwriter.tools.Constants;
import ch.admin.nb.lod.rdfwriter.tools.StringTool;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC_11;

public class Marc689 {

	public void toRdf(List<VariableField> listVariableField, Model model,
			String id) {

		String[][] kette = new String[9][9];
		String subject = "";
		System.out.println("Länge: " + kette.length);

		int i1;
		int i2;
		// RDF-Triple
		Resource rdfSubject;
		Property rdfPredicate;
		Resource rdfObject;

		for (VariableField vf : listVariableField) {
			DataField df = (DataField) vf;
			i1 = Character.getNumericValue(df.getIndicator1());
			i2 = Character.getNumericValue(df.getIndicator2());

			System.out.println(i1);
			System.out.println(i2);

			String subfieldA = "";
			String subfieldData = "";

			List<Subfield> listSubfields = df.getSubfields();
			for (Subfield sf : listSubfields) {
				// String s = sf.getData().replaceAll("( *)$", "$1");
				if (sf.getCode() == 'a') {
					subfieldA = sf.getData();
				} else {
					if (sf.getCode() != '0' && sf.getCode() != 'D'
							&& sf.getCode() != 'A') {
						if (sf.getCode() == 'd' || sf.getCode() == 'g') {
							subfieldData = subfieldData.concat(" (").concat(
									sf.getData().concat(")"));
						} else {
							subfieldData = subfieldData.concat(" ").concat(
									sf.getData());
						}
					}
				}

			}
			subject = subfieldA.concat(subfieldData);

			subject = StringTool.cleanUp(subject);
			kette[i1][i2] = subject;

		}
		String[] s = new String[9];
		for (int i = 0; i < kette.length; i++) {
			subject = "";
			for (int j = 0; j < kette.length; j++) {
				if (kette[i][j] != null) {
					subject = subject.concat(" ; ").concat(kette[i][j]);
				}
				s[i] = subject;
				s[i] = s[i].replaceAll("^ ; ", "");
			}
		}

		for (int i = 0; i < s.length; i++) {
//			System.out.println(s[i]);

			// Triple bilden (ISBN13 ohne Bindestrich)
			rdfSubject = model.createResource(Constants.NS_HELVETICAT_BIB + id);
			rdfPredicate = model.createProperty(Constants.NS_NBO_SUBJECT_RSWK);
			if (!s[i].equals("")) {
				model.add(rdfSubject, rdfPredicate, s[i]);
			}

		}
	}
}
