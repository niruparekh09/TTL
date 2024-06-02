package com.app.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Employee_Tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @NotBlank(message = "First Name Can't be Empty")
    @Column(name = "FirstName", nullable = false, length = 20)
    private String firstName;
    @NotBlank(message = "Last Name Can't be Empty")
    @Column(name = "LastName", nullable = false, length = 20)
    private String lastName;
    @NotBlank(message = "City Can't be Empty")
    @Column(name = "City", nullable = false, length = 20)
    private String city;
    @Column(name = "Salary", nullable = false, length = 20)
    @NotNull(message = "Salary cannot be empty")
    private Double salary;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email can't be Empty")
    @Column(name = "Email", unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Password is Required")
    private String password;
    @NotBlank(message = "Enter Department")
    @Column(name = "Dept", nullable = false, length = 20)
    private String dept;
    @Column(name = "Role", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

}
