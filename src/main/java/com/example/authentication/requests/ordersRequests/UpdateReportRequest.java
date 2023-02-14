package com.example.authentication.requests.ordersRequests;

import lombok.Data;

@Data
public class UpdateReportRequest {
    //order main data
    private int reportId;
    private String report;


}
