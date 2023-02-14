package com.example.authentication.requests.ordersRequests;

import lombok.Data;

@Data
public class AddReportRequest {
    private int orderId;
    private String report;

    public AddReportRequest(String report, Integer orderId) {
        this.report = report;
        this.orderId = orderId;
    }
}
