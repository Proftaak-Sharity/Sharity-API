package com.example.sharity.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberRounder {

    public static double roundDouble(double number, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static float roundFloat(float number, int places) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(number));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }
}
