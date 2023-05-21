package com.example.authentication.models;

import com.example.authentication.requests.Add.AddCategoryRequest;
import com.example.authentication.requests.Update.UpdateCategoryRequest;
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
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 150, nullable = false, unique = true)
    private String name;
    @Lob
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "photo")
    private String photo;

    public Categories(AddCategoryRequest request) {
        this.description = request.getDescription();
        this.name = request.getName();
        this.photo = request.getPhoto();
    }

    public Categories(UpdateCategoryRequest request) {
        this.id = request.getCategoryId();
        this.description = request.getDescription();
        this.name = request.getName();
        this.photo = request.getPhoto();
    }
}
