package br.com.contabancaria.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberFormatUtil {

	public static String currencyFormat(BigDecimal n) {
//		NumberFormat.getInstance(Locale.US)
		return NumberFormat.getCurrencyInstance().format(n);
	}
}
