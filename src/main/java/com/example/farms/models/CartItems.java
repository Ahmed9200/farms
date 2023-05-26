package com.example.farms.models;

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
    private int itemId;
    @Column(name = "invoice_id",nullable = false)
    private int invoiceId;

}
