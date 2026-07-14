package com.adithya.watertracker.controller;

import com.adithya.watertracker.dto.response.ApiResponse;
import com.adithya.watertracker.dto.response.DashboardResponse;
import com.adithya.watertracker.entity.User;
import com.adithya.watertracker.entity.WaterIntake;
import com.adithya.watertracker.repository.WaterRepository;
import com.adithya.watertracker.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins="*")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private WaterRepository waterRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> dashboard(

            HttpSession session){

        Long id=(Long)session.getAttribute("userId");

        User user=userService.getUserById(id);

        Double today=0.0;

        WaterIntake intake =
                waterRepository.findByUserAndIntakeDate(
                        user,
                        LocalDate.now())
                .orElse(null);

        if(intake != null){

            today = intake.getQuantityInMl();

        }

        Double progress=0.0;

        if(user.getDailyGoal()!=null &&
                user.getDailyGoal()!=0){

            progress=
                    (today/user.getDailyGoal())*100;

        }

        DashboardResponse response=
                new DashboardResponse(

                        user.getName(),

                        today,

                        user.getDailyGoal(),

                        progress,

                        LocalDate.now()

                );

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Dashboard Loaded",

                        response

                )

        );

    }

}