package com.tenafric.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
