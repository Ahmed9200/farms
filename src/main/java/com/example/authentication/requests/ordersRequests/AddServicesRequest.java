package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class AddServicesRequest {
    private int ownerId;
    private int orderId;
    private List<String> services;

    public AddServicesRequest(List<String> orderServices, int ownerId, Integer orderId) {
        this.services = orderServices;
        this.ownerId = ownerId;
        this.orderId = orderId;
    }
}
