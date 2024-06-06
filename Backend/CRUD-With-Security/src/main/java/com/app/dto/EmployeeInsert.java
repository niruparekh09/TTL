package com.app.dto;

import lombok.*;

import javax.management.relation.Role;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInsert {
    private String firstName;
    private String lastName;
    private String city;
    private Double salary;
    private String email;
    private String password;
    private String dept;
    private String role;
}
