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
@Table(name = "orders_reports")
public class OrdersReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_id")
    private int orderId;

    @Lob
    @Column(name = "order_report")
    private String orderReport;

    @Column(name = "date")
    private Date date;


    public OrdersReports(String orderReport, int orderId) {
        this.orderReport = orderReport;
        this.orderId = orderId;
        this.date = new Date();
    }
}
