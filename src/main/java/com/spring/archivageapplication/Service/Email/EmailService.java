package com.spring.archivageapplication.Service.Email;

import com.spring.archivageapplication.Dto.Mail;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;


public interface EmailService {


    public void sendCodeByEmail(Mail mail);

    public void sendEmail(SimpleMailMessage email);





}