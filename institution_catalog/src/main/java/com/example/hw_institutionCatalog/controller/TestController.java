package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.dto.out.TestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public TestDto testGet() {
        System.out.println("I am here!");
        TestDto restaurant = new TestDto();
        restaurant.setName("test");
        return restaurant;
    }
}
