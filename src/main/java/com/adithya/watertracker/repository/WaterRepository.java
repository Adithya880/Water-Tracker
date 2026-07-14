package com.adithya.watertracker.repository;

import com.adithya.watertracker.entity.User;
import com.adithya.watertracker.entity.WaterIntake;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WaterRepository
        extends JpaRepository<WaterIntake, Long> {
	Optional<WaterIntake> findByUserAndIntakeDate(
	        User user,
	        LocalDate intakeDate
	);
		Page<WaterIntake> findByUserOrderByIntakeDateDesc(
	            User user,
	            Pageable pageable
	    );
    
    Optional<WaterIntake> findById(Long id);

}

