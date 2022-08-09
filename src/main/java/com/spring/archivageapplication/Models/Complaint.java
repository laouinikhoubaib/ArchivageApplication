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
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Complaint_id;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date ComplaintDate;

    @Enumerated(EnumType.STRING)
    private ComplaintType name;

    @Enumerated(EnumType.STRING)
    ComplaintStatus complaint_status;

    private boolean Etat;

    @ManyToOne
    User user;



}
