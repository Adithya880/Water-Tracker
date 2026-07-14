package com.adithya.watertracker.dto.response;

import java.time.LocalTime;

public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private Double dailyGoal;

    private LocalTime reminderTime;

    public UserResponse(){}

    public UserResponse(

            Long id,

            String name,

            String email,

            Double dailyGoal,

            LocalTime reminderTime){

        this.id=id;

        this.name=name;

        this.email=email;

        this.dailyGoal=dailyGoal;

        this.reminderTime=reminderTime;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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