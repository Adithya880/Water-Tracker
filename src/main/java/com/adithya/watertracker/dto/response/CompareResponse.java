package com.adithya.watertracker.dto.response;

import java.time.LocalDate;

public class CompareResponse {

    private LocalDate date1;
    private Double intake1;

    private LocalDate date2;
    private Double intake2;

    private Double difference;

    private String unit;

    public CompareResponse() {
    }

    public CompareResponse(LocalDate date1,
                           Double intake1,
                           LocalDate date2,
                           Double intake2,
                           Double difference,
                           String unit) {

        this.date1 = date1;
        this.intake1 = intake1;
        this.date2 = date2;
        this.intake2 = intake2;
        this.difference = difference;
        this.unit = unit;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public Double getIntake1() {
        return intake1;
    }

    public void setIntake1(Double intake1) {
        this.intake1 = intake1;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    public Double getIntake2() {
        return intake2;
    }

    public void setIntake2(Double intake2) {
        this.intake2 = intake2;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}