package com.example.authentication.models.complaintsModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_date" , nullable = false)
    private String createdDate;

    @Column(name = "email" , nullable = false)
    private String email;


    @Column(name = "last_update_date" , nullable = true)
    private String lastUpdateDate;

    @Column(name = "close_date" , nullable = false)
    private String closeDate;

    @Column(name = "status", length = 50, nullable = true)
    private String status = "open";

    public Complaints(int userId , String email){
        this.userId = userId;
        this.email = email;
        this.status = "open";
        this.createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.lastUpdateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
