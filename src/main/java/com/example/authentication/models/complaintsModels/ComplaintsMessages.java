package com.example.authentication.models.complaintsModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaints_messages")
public class ComplaintsMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "complain_id")
    private Integer complainId;

    @Lob
    @Column(name = "complain")
    private String complain;


    @Column(name = "created_date" , nullable = false)
    private String createdDate;

    public ComplaintsMessages (int complainId , String complain){
        this.complain = complain;
        this.complainId = complainId;
        this.createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
