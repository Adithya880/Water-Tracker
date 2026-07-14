package com.adithya.watertracker.dto.response;

import java.time.LocalDate;

public class DashboardResponse {

    private String name;

    private Double todayIntake;

    private Double dailyGoal;

    private Double progress;

    private LocalDate today;

    public DashboardResponse() {
    }

    public DashboardResponse(String name,
                             Double todayIntake,
                             Double dailyGoal,
                             Double progress,
                             LocalDate today) {

        this.name = name;
        this.todayIntake = todayIntake;
        this.dailyGoal = dailyGoal;
        this.progress = progress;
        this.today = today;
    }

    public String getName() {
        return name;
    }

    public Double getTodayIntake() {
        return todayIntake;
    }

    public Double getDailyGoal() {
        return dailyGoal;
    }

    public Double getProgress() {
        return progress;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTodayIntake(Double todayIntake) {
        this.todayIntake = todayIntake;
    }

    public void setDailyGoal(Double dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

}