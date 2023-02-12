package com.example.authentication.requests.ordersRequests;

import lombok.Data;

@Data
public class TypeRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;
    private String type;

}
