package com.example.farms.models;

import com.example.farms.DTO.Add.AddContactUsDTO;
import com.example.farms.DTO.Update.UpdateContactUsRequest;
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
@Table(name = "contact_us")
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "user_email")
    private String userEmail;
    @Lob
    @Column(name = "message")
    private String message;
    @Column(name = "date")
    private Date date= new Date();

    public ContactUs(AddContactUsDTO request) {
        this.userEmail = getUserEmail();
        this.message = request.getMessage();
        this.username = request.getUsername();
        this.userPhone = request.getUserPhone();
        this.date = new Date();
    }

    public ContactUs(UpdateContactUsRequest request) {
        this.id = request.getContactId();
        this.userEmail = getUserEmail();
        this.message = request.getMessage();
        this.username = request.getUsername();
        this.userPhone = request.getUserPhone();
    }
}
