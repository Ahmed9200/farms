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
@Table(name = "invoices")
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", length = 150)
    private String username;

    @Column(name = "user_phone", length = 50, nullable = false)
    private String userPhone;

    @Lob
    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "total_before_discount", nullable = false)
    private double totalBeforeDiscount;

    @Column(name = "discount", nullable = false)
    private double discount=0;

    @Column(name = "total_after_discount", nullable = false)
    private double totalAfterDiscount;

    @Column(name = "service", nullable = false)
    private double service=0;

    @Column(name = "date")
    private Date date = new Date();

}
