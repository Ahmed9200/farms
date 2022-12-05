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
@Table(name = "complaints_responses")
public class ComplaintsResponses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "complain_id")
    private Integer complainId;


    @Column(name = "complaint_message_id")
    private Integer complaintMessageId;


    @Lob
    @Column(name = "response")
    private String response;


    @Column(name = "created_date" , nullable = false)
    private String createdDate;



    public ComplaintsResponses (int complaintMessageId ,int complainId , String response){
        this.complaintMessageId = complaintMessageId;
        this.response = response;
        this.complainId = complainId;
        this.createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


}
