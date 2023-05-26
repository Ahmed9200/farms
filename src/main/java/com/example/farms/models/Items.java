package com.example.farms.models;

import com.example.farms.DTO.Add.AddItemDTO;
import com.example.farms.DTO.Update.UpdateItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 450, nullable = false)
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "sale")
    private int sale;
    @Column(name = "new_items")
    private boolean newItems = false;
    @Column(name = "added_date" ,updatable = false)
    @CreationTimestamp
    private Date addedDate;
    @Column(name = "category_id" ,nullable = false)
    private int categoryId;
    @Lob
    @Column(name = "photo")
    private String photo;

    public Items(AddItemDTO request) {
        this.name= request.getName();
        this.newItems = request.isNewItem();
        this.price= request.getPrice();
        this.quantity= request.getQuantity();
        this.sale= request.getSale();
        this.photo= request.getPhoto();
        this.categoryId = request.getCategoryId();
    }

    public Items(UpdateItemDTO request) {
        this.id= request.getItemId();
        this.name= request.getName();
        this.newItems = request.isNewItem();
        this.price= request.getPrice();
        this.quantity= request.getQuantity();
        this.sale= request.getSale();
        this.photo= request.getPhoto();
        this.categoryId = request.getCategoryId();
    }
}
