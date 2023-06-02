package com.example.farms.models.entities;

import com.example.farms.DTO.Add.AddPartnerDTO;
import com.example.farms.DTO.Update.UpdatePartnerRequest;
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
@Table(name = "Partners")
public class Partners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "photo")
    private String photo;
    @Column(name = "content_type")
    private String contentType;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date= new Date();


    public Partners(AddPartnerDTO request) {
        this.description = request.getDescription();
        this.name = request.getName();
        this.photo = request.getPhoto();
        this.contentType=request.getContentType();
    }
    public Partners(UpdatePartnerRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.photo = request.getPhoto();
        this.id = request.getPartnerId();
        this.contentType= request.getContentType();
    }

}
