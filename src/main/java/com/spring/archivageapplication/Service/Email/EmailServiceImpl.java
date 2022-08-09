package com.spring.archivageapplication.Service.Email;

import com.spring.archivageapplication.Dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;

import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;


    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {

        javaMailSender.send(email);
    }

    @Override
    @Async
    public void sendCodeByEmail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getTo());
        mailMessage.setSubject("Code d'activation");
        mailMessage.setFrom("laouinikhoubaib@gmail.com");
        mailMessage.setText(mail.getCode());
        javaMailSender.send(mailMessage);

    }




}
