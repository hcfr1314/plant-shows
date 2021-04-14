package com.hhgs.plantshows.util;

import java.math.BigDecimal;

public class NumberalUtil {

    public static float conversionTwoDecimical(float number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        float floatValue = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return floatValue;
    }

}
