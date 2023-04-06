package com.example.authentication.models.orders;

import com.example.authentication.requests.ordersRequests.AddOrderRequest;
import com.example.authentication.requests.ordersRequests.EditOrderRequest;
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
    private int ownerId;

    @Column(name = "location_lat")
    private String locationLat;

    @Column(name = "location_lng")
    private String locationLng;

    @Column(name = "creation_date" , nullable = false)
    private Date creationDate;

    @Column(name = "scan_date")
    private Date scanDate;

    @Column(name = "execution_time")
    private String executionTime;

    @Lob
    @Column(name = "description")
    private String description;


    public Orders(AddOrderRequest request) {
        this.carModel = request.getCarModel();
        this.carType = request.getCarType();
        this.orderType = request.getOrderType();
        this.ownerId = request.getOwnerId();
        this.creationDate = new Date();
        this.executionTime = request.getExecutionTime();
        this.locationLat = request.getLocationLat();
        this.locationLng = request.getLocationLng();
        this.orderCurrentStatus = "1";
        this.description = request.getDescription();
    }

    public void setData(EditOrderRequest request , Orders order) {
        this.id = request.getOrderId();
        this.carModel = request.getCarModel();
        this.carType = request.getCarType();
        this.locationLat = request.getLocationLat();
        this.locationLng = request.getLocationLng();
        this.description = request.getDescription();
        this.orderCurrentStatus = order.getOrderCurrentStatus();
        this.creationDate = order.getCreationDate();
        this.scanDate=order.getScanDate();
        this.executionTime = request.getExecutionTime();

    }
}
