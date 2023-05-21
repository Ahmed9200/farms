package com.example.authentication.models;

import com.example.authentication.requests.Add.AddBannerRequest;
import com.example.authentication.requests.Update.UpdateBannerRequest;
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

    public Banners(AddBannerRequest request) {
        this.description1 = request.getDescription1();
        this.description2 = request.getDescription2();
        this.photo = request.getPhoto();
        this.priority = request.getPriority();
    }
    public Banners(UpdateBannerRequest request) {
        this.description1 = request.getDescription1();
        this.description2 = request.getDescription2();
        this.photo = request.getPhoto();
        this.priority = request.getPriority();
        this.id = request.getBannerId();
    }
}
