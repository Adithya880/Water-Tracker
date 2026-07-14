package com.adithya.watertracker.dto.response;

import com.adithya.watertracker.enums.WaterUnit;

import java.time.LocalDate;
import java.time.LocalTime;

public class WaterResponse {

    private Long id;
    private Double quantity;
    private WaterUnit unit;
    private LocalDate intakeDate;
    private LocalTime intakeTime;

    public WaterResponse() {
    }

    public WaterResponse(Long id,
                         Double quantity,
                         WaterUnit unit,
                         LocalDate intakeDate,
                         LocalTime intakeTime) {

        this.id = id;
        this.quantity = quantity;
        this.unit = unit;
        this.intakeDate = intakeDate;
        this.intakeTime = intakeTime;
    }

    public Long getId() {
        return id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public WaterUnit getUnit() {
        return unit;
    }

    public LocalDate getIntakeDate() {
        return intakeDate;
    }

    public LocalTime getIntakeTime() {
        return intakeTime;
    }
}