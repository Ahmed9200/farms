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
    @Column(name = "photo_content_type")
    private String photoContentType;

    public Categories(AddCategoryDTO request) {
        this.description = request.getDescription();
        this.name = request.getName();
        this.photo = request.getPhoto();
        this.photoContentType = request.getPhotoContentType();
    }

    public Categories(UpdateCategoryRequest request) {
        this.id = request.getCategoryId();
        this.description = request.getDescription();
        this.name = request.getName();
        this.photo = request.getPhoto();
        this.photoContentType = request.getPhotoContentType();
    }

    public Categories(int categoryId) {
        this.id=categoryId;
    }
}
