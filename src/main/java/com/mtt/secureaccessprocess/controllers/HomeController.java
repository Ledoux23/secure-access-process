package com.mtt.secureaccessprocess.controllers;

import com.mtt.secureaccessprocess.dto.HomeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/home")
    public HomeResponse home(){
        return new HomeResponse("Hello from Authorized API request.");
    }
}
