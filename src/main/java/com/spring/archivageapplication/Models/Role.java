package com.spring.archivageapplication.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "roles")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEn name;



    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;



}