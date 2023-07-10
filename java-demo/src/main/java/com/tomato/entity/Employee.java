package com.tomato.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Employee {

    private String name;
    private Integer age;
    private Double salary;
    private Computer computer;
    private StatusEnum status;

}
