package com.example.farms.models.entities;

import com.example.farms.DTO.Add.AddExpYearDTO;
import com.example.farms.DTO.Update.UpdateExpYearRequest;
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


    public ExperiencesYears(AddExpYearDTO request) {
        this.description = request.getDescription();
        this.year = request.getYear();
    }
    public ExperiencesYears(UpdateExpYearRequest request) {
        this.year = request.getYear();
        this.description = request.getDescription();
        this.id = request.getExpYearId();
    }
}
