package com.spring.archivageapplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtLogin {

    private String email;
    private String password;

}