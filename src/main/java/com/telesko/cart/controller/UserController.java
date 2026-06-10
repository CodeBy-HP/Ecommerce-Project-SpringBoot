package com.telesko.cart.controller;

import com.telesko.cart.model.Users;
import com.telesko.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user) {
        Users savedUser = service.registerUser(user);
        savedUser.setPassword("[PROTECTED]");
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // --- NEW LOGIN ENDPOINT ---
    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        // Returns the JWT String
        return service.verify(user);
    }
}
