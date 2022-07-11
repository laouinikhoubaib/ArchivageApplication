package com.spring.archivageapplication.Models;


import lombok.*;

import javax.persistence.*;
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
    private String email;
    private int active;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Complaint> complaints;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<File> files;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Code code;


    public User(String username, String email, String password, String firstname, String lastname, int phoneNumber, boolean b) {
    }
}
