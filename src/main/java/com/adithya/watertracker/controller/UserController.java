package com.adithya.watertracker.controller;

import com.adithya.watertracker.dto.request.ProfileRequest;
import com.adithya.watertracker.dto.request.ReminderRequest;
import com.adithya.watertracker.dto.response.ApiResponse;
import com.adithya.watertracker.dto.response.ReminderResponse;
import com.adithya.watertracker.dto.response.UserResponse;
import com.adithya.watertracker.entity.User;
import com.adithya.watertracker.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponse>> updateProfile(

            @Valid
            @RequestBody
            ProfileRequest request,

            HttpSession session){

        Long userId=(Long)session.getAttribute("userId");

        if(userId==null){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)

                    .body(new ApiResponse<>(

                            false,

                            "Please Login First"

                    ));

        }

        User user=userService.updateProfile(userId,request);
        session.setAttribute("userName", user.getName());
        session.setAttribute(
                "dailyGoal",
                user.getDailyGoal()
        );

        session.setAttribute(
                "reminderTime",
                user.getReminderTime()
        );
        UserResponse response=new UserResponse(

user.getId(),

user.getName(),

user.getEmail(),

user.getDailyGoal(),

user.getReminderTime()

);

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Profile Completed Successfully",

                        response

                )

        );

    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponse>> getProfile(

            HttpSession session){

        Long userId=(Long)session.getAttribute("userId");

        if(userId==null){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)

                    .body(new ApiResponse<>(

                            false,

                            "Please Login First"

                    ));

        }

        User user=userService.getUserById(userId);

        UserResponse response=new UserResponse(

user.getId(),

user.getName(),

user.getEmail(),

user.getDailyGoal(),

user.getReminderTime()

);

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Profile Loaded",

                        response

                )
                
      

        );

    }
    @GetMapping("/reminder")
    public ResponseEntity<ApiResponse<ReminderResponse>> getReminder(

            HttpSession session){

        Long userId = (Long) session.getAttribute("userId");

        if(userId == null){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "Please Login First"
                    ));
        }

        ReminderResponse response =
                userService.getReminder(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Reminder Loaded",
                        response
                )
        );
    }

    @PutMapping("/reminder")
    public ResponseEntity<ApiResponse<ReminderResponse>> updateReminder(

            @RequestBody
            ReminderRequest request,

            HttpSession session){

        Long userId = (Long) session.getAttribute("userId");

        if(userId == null){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "Please Login First"
                    ));
        }

        ReminderResponse response =
                userService.updateReminder(userId, request);

        session.setAttribute(
                "reminderTime",
                response.getReminderTime()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Reminder Updated Successfully",
                        response
                )
        );
    }
}