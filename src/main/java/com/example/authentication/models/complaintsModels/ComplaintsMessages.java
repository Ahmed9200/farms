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
    @Column(name = "complaint")
    private String complaint;


    @Column(name = "created_date" , nullable = false)
    private String createdDate;

    public ComplaintsMessages (int complainId , String complaint){
        this.complaint = complaint;
        this.complainId = complainId;
        this.createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
