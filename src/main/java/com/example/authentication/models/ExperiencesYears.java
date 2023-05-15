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
@Table(name = "experiences_years")
public class ExperiencesYears {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "year")
    private String year;

    @Lob
    @Column(name = "description")
    private String description;

}
