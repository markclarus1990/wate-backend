package com.msys.water_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msys.water_station.service.user.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(Pageable pageable) {
        return ResponseEntity.ok("TEST");
    }

    @GetMapping("/test2")
    public ResponseEntity<?> test2(Pageable pageable) {
        return ResponseEntity.ok("TEST2");
    }

    @GetMapping("/test3")
    public ResponseEntity<?> test3(Pageable pageable) {
        return ResponseEntity.ok("TEST3");
    }

    @GetMapping("/test4")
    public ResponseEntity<?> test4(Pageable pageable) {
        return ResponseEntity.ok("TEST");
    }

    @GetMapping("/byusernamecontains/{username}")
    public ResponseEntity<?> getUserByUsernameContains(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserContains(username));
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}