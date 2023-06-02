package com.example.farms.models.entities;

import com.example.farms.DTO.Add.AddCategoryDTO;
import com.example.farms.DTO.Update.UpdateCategoryRequest;
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
@Table(name = "configs")
public class Configs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;


    public Configs(String name , String value) {
        this.name = name;
        this.value = value;
    }
}
