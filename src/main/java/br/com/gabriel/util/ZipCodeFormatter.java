package br.com.gabriel.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class ZipCodeFormatter {

	public static String formatZipCode(Integer zipCode) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("#####-###");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formatter.setValueContainsLiteralCharacters(false);

		String formattedDocument = null;

		try {
			formattedDocument = formatter.valueToString(zipCode);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formattedDocument;
	}
	
}
