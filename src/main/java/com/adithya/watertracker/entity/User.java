package com.adithya.watertracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column
	private String name;

	@Column(name="daily_goal")
	private Double dailyGoal;

	@Column(name="reminder_time")
	private LocalTime reminderTime;

	@Column(name="created_at")
	private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WaterIntake> waterIntakes;

    public User() {
    }


    public Long getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<WaterIntake> getWaterIntakes() {
        return waterIntakes;
    }

    public void setWaterIntakes(List<WaterIntake> waterIntakes) {
        this.waterIntakes = waterIntakes;
    }
    
    @PrePersist
    public void prePersist(){

        createdAt=LocalDateTime.now();

    }
    public Double getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(Double dailyGoal) {
        this.dailyGoal = dailyGoal;
    }
}