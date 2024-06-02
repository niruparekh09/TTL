package com.app.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private String city;
    private Double salary;
    private String email;
    private String dept;
}
