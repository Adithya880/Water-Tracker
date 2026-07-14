package com.adithya.watertracker.service;

import org.springframework.data.domain.Page;
import com.adithya.watertracker.dto.request.WaterRequest;
import com.adithya.watertracker.entity.WaterIntake;
import java.time.LocalDate;
import com.adithya.watertracker.dto.response.CompareResponse;
public interface WaterService {

    WaterIntake addWaterEntry(
            WaterRequest request,
            Long userId
    );

    Page<WaterIntake> getHistory(
            Long userId,
            int page,
            int size
    );

    WaterIntake updateWaterEntry(
            Long waterId,
            Long userId,
            WaterRequest request
    );

    void deleteEntry(
            Long intakeId,
            Long userId
    );
    CompareResponse compareDates(
            Long userId,
            LocalDate date1,
            LocalDate date2
    );
}