package com.adithya.watertracker.entity;
import com.adithya.watertracker.enums.WaterUnit;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Table(
    name = "water_intake",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "intake_date"})
    }
)
public class WaterIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WaterUnit unit;

    @Column(name = "quantity_in_ml", nullable = false)
    private Double quantityInMl;
    
    @Column(name = "intake_date", nullable = false)
    private LocalDate intakeDate;

    @Column(name = "intake_time", nullable = false)
    private LocalTime intakeTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public WaterIntake() {
    }

    @PrePersist
    public void prePersist() {

        intakeDate = LocalDate.now();

        intakeTime = LocalTime.now();

        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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
    
    public LocalDate getIntakeDate() {
        return intakeDate;
    }

    public LocalTime getIntakeTime() {
        return intakeTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Double getQuantityInMl() {
        return quantityInMl;
    }

    public void setQuantityInMl(Double quantityInMl) {
        this.quantityInMl = quantityInMl;
    }
}