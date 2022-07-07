package com.spring.archivageapplication.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private Date addDate;
    private Date updateDate;
    private String updateOwner;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @ManyToOne
    User user;
}
