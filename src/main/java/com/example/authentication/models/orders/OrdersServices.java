package com.example.authentication.models.orders;

import com.example.authentication.requests.ordersRequests.AddOrderRequest;
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
@Table(name = "orders_services")
public class OrdersServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "added_by")
    private int addedBy; // owner id , or da3ma admin

    @Column(name = "order_service")
    private String orderService;

    @Column(name = "order_service_price")
    private String orderServicePrice;

    @Column(name = "date")
    private Date date;


    public OrdersServices(String orderService ,String orderServicePrice, int orderId , int ownerId) {
        this.orderId = orderId;
        this.addedBy = ownerId;
        this.date = new Date();
        this.orderService = orderService;
        this.orderServicePrice = orderServicePrice;



    }
}
