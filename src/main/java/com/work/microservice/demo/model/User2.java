package com.work.microservice.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "user_details")
public class User2 {

    @Id
    @GeneratedValue
    private Integer id;

//    @JsonProperty("user_name")
    private String name;

//    @JsonProperty("birth_dates")
    private LocalDate birthDate;

    public User2(Integer id, String name, LocalDate birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User2() {

    }
}
