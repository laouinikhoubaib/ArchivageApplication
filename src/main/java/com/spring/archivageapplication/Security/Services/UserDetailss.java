package com.spring.archivageapplication.Security.Services;


import com.spring.archivageapplication.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;


public class UserDetailss implements UserDetails {

    private static final long serialVersionUID = 1L;
    User user;
    private Long id;
    private String username;
    private String email;

    private String password;


    private Collection<? extends GrantedAuthority> authorities;




    public UserDetailss(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }



    public UserDetailss(User user) {
        super();
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive() == 1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailss user = (UserDetailss) o;
        return Objects.equals(id, user.id);
    }
}