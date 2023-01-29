package com.work.microservice.demo.filtering;


import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

//@JsonIgnoreProperties({"field1", "field2"})
@Data
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;

    private String field2;

    //@JsonIgnore
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
