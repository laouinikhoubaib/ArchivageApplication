package com.spring.archivageapplication.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountResponse {

   // private int  result;

    private int code=0;
    private String msg="";
    private int result = 0;

    public AccountResponse() {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
