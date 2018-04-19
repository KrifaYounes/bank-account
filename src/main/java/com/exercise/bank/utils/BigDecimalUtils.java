package com.exercise.bank.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

	public static BigDecimal convertNullToZero(BigDecimal amount) {
		return amount != null ? amount : BigDecimal.ZERO;
	}
}
