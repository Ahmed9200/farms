package com.example.authentication.requests.ordersRequests;

import lombok.Data;

@Data
public class DateRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;
    private String startDate;
    private String endDate;
}
