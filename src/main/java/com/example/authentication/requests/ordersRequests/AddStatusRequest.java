package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class AddStatusRequest {
    private int orderId;
    private String status;

    public AddStatusRequest(String status, Integer orderId) {
        this.status = status;
        this.orderId = orderId;
    }
}
