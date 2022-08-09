package com.spring.archivageapplication.Security.Services;


import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.AuthRepository;
import com.spring.archivageapplication.Repository.userRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailssService implements UserDetailsService {

    private AuthRepository userRepository;

    public UserDetailssService(AuthRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        //System.out.println(user.getEmail() + "         " +user.getPassword());
        UserDetailss userPrincipal = new UserDetailss(user);
        return userPrincipal;
    }

    @Transactional
    public void addUser(User user){

        userRepository.save(user);
    }

    public boolean ifEmailExist(String email){

        return userRepository.existsByEmail(email);
    }

    @Transactional
    public int getUserActive(String email){

        return userRepository.getActive(email);
    }

    @Transactional
    public String getPasswordByEmail(String email){

        return userRepository.getPasswordByEmail(email);
    }

    public User getUserByMail(String mail){

        return this.userRepository.findByEmail(mail);
    }
    public void editUser(User user){

        this.userRepository.save(user);
    }
}
