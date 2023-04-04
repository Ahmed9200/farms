package com.example.authentication.models.orders;

import com.example.authentication.requests.ordersRequests.AddOrderTimingRequest;
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
@Table(name = "orders_timing")
public class OrdersTiming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "day" , nullable = false)
    private String day;

    @Column(name = "time_from")
    private String timeFrom;

    @Column(name = "time_to")
    private String timeTo;


    public OrdersTiming(AddOrderTimingRequest request) {

        this.day = request.getDay();
        this.timeFrom = request.getFrom();
        this.timeTo =request.getTo();
    }

}
