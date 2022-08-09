package com.spring.archivageapplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSignUp {

    private String email;
    private String password;
    private String username;
    private String firstname;
    private String lastname;
    private int phoneNumber;
    private Set<String> role;


}