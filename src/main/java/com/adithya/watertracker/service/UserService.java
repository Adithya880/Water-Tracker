package com.adithya.watertracker.service;

import com.adithya.watertracker.dto.request.LoginRequest;
import com.adithya.watertracker.dto.request.ProfileRequest;
import com.adithya.watertracker.dto.request.SignupRequest;
import com.adithya.watertracker.entity.User;
import com.adithya.watertracker.dto.request.ReminderRequest;
import com.adithya.watertracker.dto.response.ReminderResponse;
public interface UserService {

    User registerUser(
            SignupRequest request);

    User loginUser(
            LoginRequest request);

    User updateProfile(
            Long userId,
            ProfileRequest request);

    User getUserById(
            Long userId);
    ReminderResponse getReminder(
            Long userId
    );

    ReminderResponse updateReminder(
            Long userId,
            ReminderRequest request
    );
}