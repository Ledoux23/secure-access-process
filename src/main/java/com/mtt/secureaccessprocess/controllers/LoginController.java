package com.mtt.secureaccessprocess.controllers;

import com.mtt.secureaccessprocess.dto.LoginRequest;
import com.mtt.secureaccessprocess.dto.LoginResponse;
import com.mtt.secureaccessprocess.services.jwt.CustomerServiceImpl;
import com.mtt.secureaccessprocess.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authentication;
    private final CustomerServiceImpl customerService;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authentication, CustomerServiceImpl customerService, JwtUtil jwtUtil) {
        this.authentication = authentication;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        try {
            authentication.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails;
        try {
            userDetails = customerService.loadUserByUsername(loginRequest.getEmail());
        } catch(UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        //Additional logic can be added here if needed

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}

/*

import com.mtt.secureaccessprocess.dto.LoginRequest;
import com.mtt.secureaccessprocess.dto.LoginResponse;
import com.mtt.secureaccessprocess.services.jwt.CustomerServiceImpl;
import com.mtt.secureaccessprocess.utils.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AuthenticationManager authentication;
    private final CustomerServiceImpl customerService;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authentication, CustomerServiceImpl customerService, JwtUtil jwtUtil) {
        this.authentication = authentication;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        logger.info("Attempting login with email: {}", loginRequest.getEmail());

        try {
            authentication.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for email: {}", loginRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails;
        try {
            userDetails = customerService.loadUserByUsername(loginRequest.getEmail());
        } catch(UsernameNotFoundException e) {
            logger.error("User not found for email: {}", loginRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            logger.error("An unexpected error occurred while loading user by username: {}", loginRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        String jwt;
        try {
            jwt = jwtUtil.generateToken(userDetails.getUsername());
        } catch(Exception e) {
            logger.error("An error occurred while generating JWT token for user: {}", userDetails.getUsername(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        logger.info("JWT generated successfully for user: {} with jwt : {}", userDetails.getUsername(), jwt);
        //Additional logic can be added here if needed

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}
*/