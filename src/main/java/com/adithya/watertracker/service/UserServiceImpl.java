package com.adithya.watertracker.service;

import com.adithya.watertracker.dto.request.LoginRequest;
import com.adithya.watertracker.dto.request.ProfileRequest;
import com.adithya.watertracker.dto.request.SignupRequest;
import com.adithya.watertracker.dto.response.UserResponse;
import com.adithya.watertracker.entity.User;
import com.adithya.watertracker.exception.UserAlreadyExistsException;
import com.adithya.watertracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.adithya.watertracker.dto.request.ReminderRequest;
import com.adithya.watertracker.dto.response.ReminderResponse;
import com.adithya.watertracker.exception.UserNotFoundException;
import com.adithya.watertracker.exception.InvalidCredentialsException;
import com.adithya.watertracker.dto.request.ProfileRequest;
import com.adithya.watertracker.exception.UserNotFoundException;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(SignupRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){

            throw new UserAlreadyExistsException("Email already registered.");

        }

        User user = new User();


        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);

    }
    
    @Override
    public User loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return user;
    }
    
    @Override
    public User updateProfile(
            Long userId,
            ProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        user.setName(request.getName());

        user.setDailyGoal(request.getDailyGoal());

        user.setReminderTime(request.getReminderTime());
        return userRepository.save(user);
    }
    
    @Override
    public User getUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

    }
    
    private UserResponse convertToResponse(User user){

        return new UserResponse(

user.getId(),

user.getName(),

user.getEmail(),

user.getDailyGoal(),

user.getReminderTime()

);

    }
    @Override
    public ReminderResponse getReminder(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return new ReminderResponse(user.getReminderTime());
    }
    
    @Override
    public ReminderResponse updateReminder(
            Long userId,
            ReminderRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        user.setReminderTime(request.getReminderTime());

        userRepository.save(user);

        return new ReminderResponse(user.getReminderTime());
    }
   
}