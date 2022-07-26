package com.spring.archivageapplication.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private int phoneNumber;

    private String password;
    @Email
    private String email;

    private int active;
    private boolean isEnabled;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<Complaint> complaints;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<File> files;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Code code;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password, String firstname, String lastname, int phoneNumber, boolean b) {
    }

    public User(String firstname, String lastname, String username, int phoneNumber, String password, String email, boolean isEnabled) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.isEnabled = isEnabled;
    }


}