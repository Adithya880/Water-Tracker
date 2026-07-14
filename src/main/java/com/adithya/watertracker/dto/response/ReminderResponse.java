package com.adithya.watertracker.dto.response;

import java.time.LocalTime;

public class ReminderResponse {

    private LocalTime reminderTime;

    public ReminderResponse() {
    }

    public ReminderResponse(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}