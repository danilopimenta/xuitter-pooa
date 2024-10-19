package com.xuitter.xuitter.controller;

import com.xuitter.xuitter.dto.UserResponse;
import com.xuitter.xuitter.model.User;
import com.xuitter.xuitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody User user) {
        try {
            User newUser = userService.registerNewUser(user.getUsername(), user.getEmail(), user.getPassword());
            String token = userService.generateToken(newUser);
            UserResponse userResponse = new UserResponse(newUser.getUsername(), newUser.getEmail(), token);
            return ResponseEntity.ok(userResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            String token = userService.generateToken(user);
            return ResponseEntity.ok(new UserResponse(user.getUsername(), user.getEmail(), token).toString());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}