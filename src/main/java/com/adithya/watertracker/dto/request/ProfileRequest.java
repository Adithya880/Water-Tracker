package com.adithya.watertracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class ProfileRequest {

    @NotBlank(message="Name is required")
    private String name;

    @NotNull(message="Daily Goal is required")
    private Double dailyGoal;

    @NotNull(message="Reminder Time is required")
    private LocalTime reminderTime;

    public ProfileRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(Double dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }

}