package com.example.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact_us")
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_email")
    private String userEmail;

    @Lob
    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private Date date= new Date();

}
