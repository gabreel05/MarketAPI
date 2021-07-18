package br.com.gabriel.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyConverter {

	public static String convertDoubleToCurrency(Double value) {
		Locale locale = new Locale("pt", "BR");

		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

		return formatter.format(value);
	}
	
}
