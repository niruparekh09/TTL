package com.app.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDeleteResponse {
    private Long id;
    private String message;
}
