package com.mtt.secureaccessprocess.controllers;

import com.mtt.secureaccessprocess.dto.WelcomeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @GetMapping("/welcome")
    public WelcomeResponse home(){
        return new WelcomeResponse("Hello from Authorized API request.");
    }
}
