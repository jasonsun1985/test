package com.collect.any;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestMath {
	public static void main(String[] args) {
		BigDecimal rate = new BigDecimal(45632);
		System.out.println(rate.divide(new BigDecimal(1024),2,RoundingMode.HALF_UP));
	}
}
