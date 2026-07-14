package com.adithya.watertracker.controller;
import com.adithya.watertracker.dto.request.LoginRequest;
import com.adithya.watertracker.dto.request.SignupRequest;
import com.adithya.watertracker.dto.response.ApiResponse;
import com.adithya.watertracker.dto.response.UserResponse;
import com.adithya.watertracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adithya.watertracker.entity.User;
import jakarta.servlet.http.HttpSession;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(

            @Valid
            @RequestBody SignupRequest request,

            HttpSession session) {

        User user =
                userService.registerUser(request);

        session.setAttribute(
                "userId",
                user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(
                        true,
                        "Account created successfully"
                ));
    }    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(

            @Valid @RequestBody LoginRequest request,
            HttpSession session) {

        User user = userService.loginUser(request);

        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getName());
        session.setAttribute("userEmail", user.getEmail());

        UserResponse response = new UserResponse(

user.getId(),

user.getName(),

user.getEmail(),

user.getDailyGoal(),

user.getReminderTime()

);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Login Successful",
                        response
                )
        );
    }
    
    @GetMapping("/session")
    public ResponseEntity<ApiResponse<UserResponse>> getSession(
            HttpSession session) {

        Long id = (Long) session.getAttribute("userId");

        if (id == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Please login first"));

        }

        UserResponse response = new UserResponse();

        response.setId(id);
        response.setName((String) session.getAttribute("userName"));
        response.setEmail((String) session.getAttribute("userEmail"));

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Session Active",
                        response
                )
        );

    }
    
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(
            HttpSession session){

        session.invalidate();

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Logout Successful"

                )

        );

    }
}