package com.example.authentication.models;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, nullable = true, unique = true)
    private String name;

    @Column(name = "email", length = 350, unique = true , nullable = true)
    private String email;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "date_of_join" , nullable = false)
    private Date dateOfJoin;


    @Column(name = "phone", length = 50 , unique = true , nullable = false)
    private String phone;

    @Column(name = "additional_phone", length = 45, nullable = true)
    private String additionalPhone; // new

    @Lob
    @Column(name = "photo")
    private String photo;


    @Column(name = "account_status", length = 50, nullable = true)
    private String accountStatus = "active";


    @Column(name = "notification_token", length = 450, nullable = true)
    private String notificationToken; // new


    public Users (String phone , String password , String notificationToken){
        this.username=phone;
        this.phone=phone;
        this.name= phone;
        this.password=password;
        this.notificationToken = notificationToken;
        this.dateOfJoin=new Date();
        this.accountStatus="active";
    }

    public Map<Object,Object> lightUser(Users u){
        Map<Object,Object> res = new HashMap<>();
        res.put("id" , u.getId());
        res.put("name",u.getName());
        res.put("email",u.getEmail());
        res.put("accountStatus",u.getAccountStatus());
        res.put("phone",u.getPhone());
        res.put("additionalPhone",u.getAdditionalPhone());
        res.put("photo",u.getPhoto());
        res.put("dateOfJoin",u.getDateOfJoin());
        return res;
    }

}
