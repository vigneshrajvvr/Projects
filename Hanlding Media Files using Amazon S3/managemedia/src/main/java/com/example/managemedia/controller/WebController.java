package com.example.managemedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello world I'm on the web!";
    }

}
