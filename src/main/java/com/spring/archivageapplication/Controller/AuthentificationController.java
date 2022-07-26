package com.spring.archivageapplication.Controller;


import com.spring.archivageapplication.Dto.*;
import com.spring.archivageapplication.Models.Code;
import com.spring.archivageapplication.Models.Role;
import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.AuthRepository;
import com.spring.archivageapplication.Repository.RoleRepository;
import com.spring.archivageapplication.Security.Jwt.TokenService;
import com.spring.archivageapplication.Security.Services.UserDetailssService;
import com.spring.archivageapplication.Service.Email.EmailService;
import com.spring.archivageapplication.Service.Email.VerificationCode;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@SecurityRequirement(name = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthentificationController {

    @Autowired
    private EmailService emailService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailssService userServiceAuth;
    @Autowired
    AuthRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/signin")
    public LoginResponse logIn(@RequestBody JwtLogin jwtLogin) {

        return tokenService.login(jwtLogin);
    }

    @PostMapping("/signup")
    public AccountResponse createUser(@RequestBody JwtSignUp jwtsignup) {

        AccountResponse accountResponse = new AccountResponse();
        boolean result = userServiceAuth.ifEmailExist(jwtsignup.getEmail());
        if (result) {
            accountResponse.setResult(500);
            accountResponse.setMsg("Registration failed");
            accountResponse.setCode(1);

        } else {
            String myCode = VerificationCode.getCode();
            User user = new User();
            user.setEmail(jwtsignup.getEmail());
            user.setPassword(encoder.encode(jwtsignup.getPassword()));
            user.setUsername(jwtsignup.getUsername());
            user.setFirstname(jwtsignup.getFirstname());
            user.setLastname(jwtsignup.getLastname());
            user.setPhoneNumber(jwtsignup.getPhoneNumber());
            Set<String> strRoles = jwtsignup.getRole();
            userRepository.save(user);
            user.setActive(0);


                Mail mail = new Mail(jwtsignup.getEmail(), myCode);
                emailService.sendCodeByEmail(mail);
                Code code = new Code();
                code.setCode(myCode);
                user.setCode(code);
                userServiceAuth.addUser(user);
                accountResponse.setResult(200);
                accountResponse.setMsg("Successful Registration");
                accountResponse.setCode(2);

            }
            return accountResponse;

        }

    @PostMapping("/active")
    public UserActive getActiveUser (@RequestBody JwtLogin jwtLogin){
        String enPassword = userServiceAuth.getPasswordByEmail(jwtLogin.getEmail()); // from
        // DB
        boolean result = encoder.matches(jwtLogin.getPassword(), enPassword); // Sure
        UserActive userActive = new UserActive();
        if (result) {
            int act = userServiceAuth.getUserActive(jwtLogin.getEmail());
            if (act == 0) {
                String code = VerificationCode.getCode();
                Mail mail = new Mail(jwtLogin.getEmail(), code);
                emailService.sendCodeByEmail(mail);
                User user = userServiceAuth.getUserByMail(jwtLogin.getEmail());
                user.getCode().setCode(code);
                userServiceAuth.editUser(user);
            }
            userActive.setActive(act);
        } else {
            userActive.setActive(-1);
        }
        return userActive;
    }



    @PostMapping("/activated")
    public AccountResponse activeAccount (@RequestBody ActiveAccount activeAccount){
        User user = userServiceAuth.getUserByMail(activeAccount.getMail());
        AccountResponse accountResponse = new AccountResponse();
        if (user.getCode().getCode().equals(activeAccount.getCode())) {
            user.setActive(1);
            userServiceAuth.editUser(user);
            accountResponse.setResult(1);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("laouinikhoubaib@gmail.com");
            mailMessage.setText("Congratuations ! Your Account has been activated  and email is verified");
            emailService.sendEmail(mailMessage);
        } else {
            accountResponse.setResult(0);
        }

        return accountResponse;
    }

    @PostMapping("/checkEmail")
    public AccountResponse resetPasswordEmail(@RequestBody ResetPassword resetPassword) {
         boolean result = this.userServiceAuth.ifEmailExist(resetPassword.getEmail());
        User user = userServiceAuth.getUserByMail(resetPassword.getEmail());
        AccountResponse accountResponse = new AccountResponse();
        if (user != null) {
            String code = VerificationCode.getCode();
            Mail mail = new Mail(resetPassword.getEmail(), code);
            emailService.sendCodeByEmail(mail);
            user.getCode().setCode(code);
            userServiceAuth.editUser(user);
            accountResponse.setResult(1);

        } else {
            accountResponse.setResult(0);

        }
        return accountResponse;
    }

    @PostMapping("/resetPassword")
    public AccountResponse resetPassword(@RequestBody NewPassword newPassword) {
        User user = userServiceAuth.getUserByMail(newPassword.getEmail());
        AccountResponse accountResponse = new AccountResponse();
        if (user != null) {
            if (user.getCode().getCode().equals(newPassword.getCode())) {
                user.setPassword(encoder.encode(newPassword.getPassword()));
                userServiceAuth.addUser(user);
                accountResponse.setResult(1);
            } else {
                accountResponse.setResult(0);
            }
        } else {
            accountResponse.setResult(0);
        }
        return accountResponse;
    }



}
