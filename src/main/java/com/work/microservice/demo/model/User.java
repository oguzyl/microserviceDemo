package com.work.microservice.demo.model;


import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private String surName;

    public User(int id, String name, String surName) {
        this.id = id;
        this.name = name;   
        this.surName = surName;
    }
}
