package com.spring.archivageapplication.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;



}