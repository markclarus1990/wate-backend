package com.msys.water_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msys.water_station.Model.User;
import com.msys.water_station.dto.user.Register;
import com.msys.water_station.dto.user.UserResponse;
import com.msys.water_station.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        return ResponseEntity.ok(authService.verify(user));

    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Register user) {
        UserResponse registeredUser = authService.Register(user);
        System.out.println("Registered User: " + user);
        return ResponseEntity.ok(registeredUser);
    }
}
