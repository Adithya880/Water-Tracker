package com.adithya.watertracker.dto.request;

import com.adithya.watertracker.enums.WaterUnit;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WaterRequest {

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Double quantity;

    @NotNull(message = "Unit is required")
    private WaterUnit unit;

    public WaterRequest() {
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public WaterUnit getUnit() {
        return unit;
    }

    public void setUnit(WaterUnit unit) {
        this.unit = unit;
    }
}