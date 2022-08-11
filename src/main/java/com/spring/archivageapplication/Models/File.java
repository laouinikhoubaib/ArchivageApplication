package com.spring.archivageapplication.Models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "file")
public class File {


  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int idfile ;
    private String name;
    private String type;

  private String url;

    private LocalDateTime uploadDate=LocalDateTime.now();

    @Lob
    private byte[] data;

    @ManyToOne
    User user;


  public File(String name,  String type,byte[] data) {

    this.name = name;
    this.data = data;
    this.type = type;
  }

}
