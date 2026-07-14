package com.adithya.watertracker.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public class ReminderRequest {

    @NotNull(message = "Reminder time is required")
    private LocalTime reminderTime;

    public ReminderRequest() {
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}