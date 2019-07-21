package com.mlsolarsystem.utils;

import java.math.BigDecimal;

/**
 * Created by tom
 */
public class MathUtils {


    private MathUtils() {
        super();
    }

    public static double scaled(double input){
        return BigDecimal.valueOf(input).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
