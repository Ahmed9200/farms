package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class UpdateServicesRequest {
    private int serviceId;
    private int orderId;
    private String price;
    private String addedBy;
    private String status;
    private String service;

    public UpdateServicesRequest(int serviceId,int orderId, String price,String addedBy,String status,String service) {
        this.orderId=orderId;
        this.serviceId = serviceId;
        this.price = price;
        this.addedBy = addedBy;
        this.status = status;
        this.service = service;
    }
}
