package com.example.authentication.models;

import com.example.authentication.requests.Add.AddPartnerRequest;
import com.example.authentication.requests.Update.UpdatePartnerRequest;
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
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date= new Date();


    public Partners(AddPartnerRequest request) {
        this.description = request.getDescription();
        this.name = request.getName();
        this.photo = request.getPhoto();
    }
    public Partners(UpdatePartnerRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.photo = request.getPhoto();
        this.id = request.getPartnerId();
    }

}
