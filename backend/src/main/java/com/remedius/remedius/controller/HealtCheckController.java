package com.remedius.remedius.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealtCheckController {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello, World!";
    }
}
