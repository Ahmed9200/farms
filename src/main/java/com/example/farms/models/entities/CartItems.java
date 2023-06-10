package com.example.farms.models.entities;

import com.example.farms.DTO.Add.AddCartDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity",nullable = false)
    private int quantity=0;
    @Column(name = "item_id", nullable = false)
    private int itemId=0;
    @Column(name = "price", nullable = false)
    private double price=0;
    @Column(name = "sale", nullable = false)
    private int sale=0;
    @Column(name = "invoice_id",nullable = false)
    private int invoiceId;

    public CartItems(AddCartDto r, int invoiceId) {
        this.quantity=r.getQuantity();
        this.itemId=r.getItemId();
        this.price=r.getPrice();
        this.sale=r.getSale();
        this.invoiceId=invoiceId;
    }
}
