package com.spring.archivageapplication.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;





@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;

    public Role(String name) {

        this.name = name;
    }
}
