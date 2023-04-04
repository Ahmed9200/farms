package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderTimingRequest {
    private String day;
    private String from;
    private String to;

}
