package com.onlineshopping.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshopping.onlineshop.PasswordEncoder;
import com.onlineshopping.onlineshop.models.UserModel;
import com.onlineshopping.onlineshop.services.UserRepo;

@RestController
public class UserController {
    
    @Autowired
     UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel user) {
        if (userRepo.findByUsername(user.getUsername()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username not registered");
        }
        UserModel existingUser = userRepo.findByUsername(user.getUsername());
        if (!PasswordEncoder.validatePassword(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
        }
        String token = PasswordEncoder.generateToken(user.getUsername());
        existingUser.setToken(token);
        userRepo.save(existingUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logged in");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UserModel user) {
        UserModel existingUser = userRepo.findByUsername(user.getUsername());
        existingUser.setToken(null);
        userRepo.save(existingUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logged out");
    }

}
