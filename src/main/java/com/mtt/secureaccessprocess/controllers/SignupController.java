package com.mtt.secureaccessprocess.controllers;

import com.mtt.secureaccessprocess.dto.SignupRequest;
import com.mtt.secureaccessprocess.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> signupCustomer(@RequestBody SignupRequest signupRequest){

        boolean isUserCreated = authService.createCustomer(signupRequest);

        if (isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
        }

    }
}
