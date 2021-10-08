package com.example.sharity.abstracts;

import java.math.BigDecimal;
import java.math.RoundingMode;

//TODO DELETE ABSTRACT LIKE IN EMAILVALIDATOR
public abstract class NumberRounder {

//    TODO DELETE STATIC LIKE IN EMAILVALIDATOR
    public static double roundDouble(double number, int places) {

        BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    //    TODO DELETE STATIC LIKE IN EMAILVALIDATOR
    public static float roundFloat(float number, int places) {

        BigDecimal bigDecimal = new BigDecimal(Float.toString(number));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }
}
