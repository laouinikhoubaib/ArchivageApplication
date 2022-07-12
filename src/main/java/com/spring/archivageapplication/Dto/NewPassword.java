package com.spring.archivageapplication.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPassword {

    private String email;
    private String code;
    private String password;
}
