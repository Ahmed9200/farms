package com.example.authentication.models.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders_status")
public class OrdersStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "date")
    private String date;

    @Lob
    @Column(name = "description")
    private String description;


}
