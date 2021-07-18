package br.com.gabriel.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class DocumentFormatter {

	public static String documentFormatter(String document) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formatter.setValueContainsLiteralCharacters(false);

		String formattedDocument = null;

		try {
			formattedDocument = formatter.valueToString(document);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formattedDocument;
	}

}
