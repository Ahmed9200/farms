package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class EditOrderRequest {
    //order main data
    private int orderId;
    private String carType;
    private String carModel;
    private String locationLat;
    private String locationLng;
    private String description;
    private String executionTime;


}
