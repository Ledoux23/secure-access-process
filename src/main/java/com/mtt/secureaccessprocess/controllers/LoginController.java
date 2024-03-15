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
