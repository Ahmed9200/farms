package com.example.authentication.models;

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
@Table(name = "admins")
public class AdminUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", length = 350, unique = true , nullable = false)
    private String email;

    @Column(name = "username", length = 350, unique = true , nullable = false)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "date_of_join" , nullable = false)
    private Date dateOfJoin;

    @Column(name = "role", length = 50, nullable = true)
    private String role = "3";

    @Column(name = "email_status", length = 50, nullable = true)
    private String emailStatus = "notActive";

    @Column(name = "account_status", length = 50, nullable = true)
    private String accountStatus = "active";


    public AdminUsers(String email , String password){
        this.email=email;
        this.password=password;
        this.username=email;
        this.dateOfJoin=new Date();
        this.accountStatus="active";
        this.emailStatus="notActive";
        this.role="3";
    }

    public Map<Object,Object> lightAdminUser(AdminUsers u){
        Map<Object,Object> res = new HashMap<>();
        res.put("id" , u.getId());
        res.put("email",u.getEmail());
        res.put("accountStatus",u.getAccountStatus());
        res.put("emailStatus",u.getEmailStatus());
        res.put("role",u.getRole());
        res.put("dateOfJoin",u.getDateOfJoin());
        return res;
    }

}
