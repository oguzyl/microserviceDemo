package com.work.microservice.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;

@RestController
@Component
public class HelloWorldController {


    private MessageSource messageSource;

//    public HelloWorldController(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }


    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World.";
    }
    @GetMapping(path = "/hello-word-international")
    public String helloWorldInternational() {
        Locale locale = LocaleContextHolder.getLocale();
        return  messageSource.getMessage("good.morning.message", null, "default", locale);
    }

}