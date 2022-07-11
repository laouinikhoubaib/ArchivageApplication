package com.spring.archivageapplication.Service.Email;

import java.util.UUID;

public class VerificationCode {

    public static String getCode(){

        return UUID.randomUUID().toString();
    }

}
