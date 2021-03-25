package com.activity.tracker.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

    public TestController() {
    }

    @GetMapping("/")
    public String getTodos() {
        return "hello world of API NEW";
    }
}

