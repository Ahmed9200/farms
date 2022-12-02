package com.example.authentication.models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
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
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "emain", length = 350, unique = true)
    private String email;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "date_of_join")
    private String dateOfJoin;


    @Column(name = "phone", length = 50 , unique = true , nullable = false)
    private String phone;

    @Column(name = "additional_phone", length = 45, nullable = true)
    private String additionalPhone; // new

    @Column(name = "photo", length = 50, nullable = true)
    private String photo;


    @Column(name = "account_status", length = 50, nullable = true)
    private String accountStatus = "active";


}
