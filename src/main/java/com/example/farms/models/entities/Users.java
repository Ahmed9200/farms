package com.example.farms.models.entities;

import com.example.farms.models.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @Column(name = "date_of_join" , nullable = false)
    @CreationTimestamp
    private Date dateOfJoin;
    @Column(name = "phone", length = 50 , unique = true)
    private String phone;
    @Column(name = "role",nullable = false)
    private UserRole role = UserRole.USER;

    public Users (String name ,String username , String password , String phone , UserRole role){
        this.username=username;
        this.phone=phone;
        this.name= name;
        this.password=password;
        this.role=role;
    }

    public Map<Object,Object> lightUser(Users u){
        Map<Object,Object> res = new HashMap<>();
        res.put("id" , u.getId());
        res.put("name",u.getName());
        res.put("username",u.getUsername());
        res.put("phone",u.getPhone());
        res.put("role",u.getRole());
        res.put("dateOfJoin",u.getDateOfJoin());
        return res;
    }

}
