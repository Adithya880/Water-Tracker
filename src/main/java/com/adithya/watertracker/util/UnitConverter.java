package com.adithya.watertracker.util;

import com.adithya.watertracker.enums.WaterUnit;

public class UnitConverter {

    private UnitConverter() {
    }

    public static Double convertToMl(Double quantity,
                                     WaterUnit unit) {

        switch (unit) {

            case ML:
                return quantity;

            case LITRE:
                return quantity * 1000;

            case GLASS:
                return quantity * 250;

            default:
                throw new IllegalArgumentException(
                        "Invalid Water Unit"
                );
        }

    }

}