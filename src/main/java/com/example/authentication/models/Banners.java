package com.example.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banners")
public class Banners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name = "description1")
    private String description1;

    @Lob
    @Column(name = "description2")
    private String description2;

    @Lob
    @Column(name = "photo")
    private String photo;

    @Column(name = "priority" , nullable = false)
    private int priority = 1;

}
