package com.app.dto;

import com.app.model.Role;
import lombok.*;

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
    private Role role;
}
