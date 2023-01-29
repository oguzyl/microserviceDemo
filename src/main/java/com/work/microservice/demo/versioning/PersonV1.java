package com.work.microservice.demo.versioning;

import lombok.Data;

@Data
public class PersonV1 {

    private String name;

    public PersonV1(String name) {
        super();
        this.name = name;
    }
}
