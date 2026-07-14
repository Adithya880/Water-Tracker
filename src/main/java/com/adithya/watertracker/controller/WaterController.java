package com.adithya.watertracker.controller;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.adithya.watertracker.dto.response.CompareResponse;
import com.adithya.watertracker.dto.request.WaterRequest;
import com.adithya.watertracker.dto.response.ApiResponse;
import com.adithya.watertracker.dto.response.WaterResponse;
import com.adithya.watertracker.entity.WaterIntake;
import com.adithya.watertracker.service.WaterService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;
@RestController
@RequestMapping("/api/water")
@CrossOrigin(origins = "*")
public class WaterController {

    @Autowired
    private WaterService waterService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<WaterResponse>> addWater(
            @Valid @RequestBody WaterRequest request,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Please login first"));

        }

        WaterIntake intake = waterService.addWaterEntry(request, userId);

        WaterResponse response = new WaterResponse(
                intake.getId(),
                intake.getQuantity(),
                intake.getUnit(),
                intake.getIntakeDate(),
                intake.getIntakeTime()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Water intake added successfully",
                                response
                        )
                );
    }
    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<WaterResponse>>> history(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            HttpSession session) {

        Long userId =
                (Long) session.getAttribute("userId");

        if (userId == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "Please login first"
                    ));

        }

        Page<WaterIntake> history =
                waterService.getHistory(
                        userId,
                        page,
                        size
                );

        List<WaterResponse> response =
                history.stream()

                        .map(w -> new WaterResponse(

                                w.getId(),

                                w.getQuantity(),

                                w.getUnit(),

                                w.getIntakeDate(),

                                w.getIntakeTime()

                        ))

                        .toList();

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "History Loaded",

                        response

                )

        );

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WaterResponse>> updateWater(

            @PathVariable Long id,

            @Valid @RequestBody WaterRequest request,

            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false,
                            "Please login first"));

        }

        WaterIntake intake =
                waterService.updateWaterEntry(
                        id,
                        userId,
                        request
                );

        WaterResponse response =
                new WaterResponse(

                        intake.getId(),

                        intake.getQuantity(),

                        intake.getUnit(),

                        intake.getIntakeDate(),

                        intake.getIntakeTime()

                );

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Water entry updated successfully",

                        response

                )

        );

    }
    @GetMapping("/compare")
    public ResponseEntity<ApiResponse<CompareResponse>> compareWaterIntake(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date1,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date2,

            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "Please login first"
                    ));
        }

        CompareResponse response =
                waterService.compareDates(
                        userId,
                        date1,
                        date2
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Comparison Successful",
                        response
                )
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteWaterEntry(
            @PathVariable Long id,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Please login first"));
        }

        waterService.deleteEntry(id, userId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Water entry deleted successfully")
        );
    }
}