package com.exercise.receiptProcessor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String getDefault(){
        return "Receipt Processor API is running!! Continue using Postman to test the API's";
    }
}
