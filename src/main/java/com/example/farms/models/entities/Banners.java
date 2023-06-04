package com.example.farms.models.entities;

import com.example.farms.DTO.Add.AddBannerDTO;
import com.example.farms.DTO.Update.UpdateBannerRequest;
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
    @Column(name = "photo_content_type")
    private String photoContentType;
    @Column(name = "priority" , nullable = false)
    private int priority = 1;

    public Banners(AddBannerDTO request) {
        this.description1 = request.getDescription1();
        this.description2 = request.getDescription2();
        this.photo = request.getPhoto();
        this.photoContentType = request.getPhotoContentType();
        this.priority = request.getPriority();
    }
    public Banners(UpdateBannerRequest request) {
        this.description1 = request.getDescription1();
        this.description2 = request.getDescription2();
        this.photo = request.getPhoto();
        this.photoContentType = request.getPhotoContentType();
        this.priority = request.getPriority();
        this.id = request.getBannerId();
    }
}
