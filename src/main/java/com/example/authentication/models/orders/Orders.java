package com.example.authentication.models.orders;

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
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_type" , nullable = false)
    private String orderType;

    @Column(name = "order_current_status")
    private String orderCurrentStatus;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "location_lat")
    private String locationLat;

    @Column(name = "location_lng")
    private String locationLng;

    @Column(name = "creation_date" , nullable = false)
    private Date creationDate;

    @Column(name = "scan_date")
    private Date scanDate;

    @Lob
    @Column(name = "description")
    private String description;


}
