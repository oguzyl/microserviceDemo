package com.work.microservice.demo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "user_details")
public class User {

    @Id
    @SequenceGenerator(name = "userIdSequence", sequenceName = "user_details_seq", allocationSize = 1)
    @GeneratedValue(generator = "userIdSequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

//    @JsonProperty("user_name")
    private String name;

//    @JsonProperty("birth_dates")
    private LocalDate birthDate;

    public User(Integer id, String name, LocalDate birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {

    }
}
