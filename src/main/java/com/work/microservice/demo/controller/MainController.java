package com.work.microservice.demo.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class MainController {

    @GetMapping(path = "/hello")
    public String helloWorld() {

        return "Hello World.";
    }

}
