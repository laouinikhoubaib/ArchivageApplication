package com.spring.archivageapplication.Dto;

import lombok.*;

@Data
@Getter
@Setter
public class UserActive {


    private int active=0;
    private String msg="";
    private int result = 0;

    public UserActive() {
        this.active = active;
        this.msg = msg;
        this.result = result;
    }
}
