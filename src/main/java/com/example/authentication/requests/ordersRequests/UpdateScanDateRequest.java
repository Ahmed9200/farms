package com.example.authentication.requests.ordersRequests;

import lombok.Data;

@Data
public class UpdateScanDateRequest {
    //order main data
    private int orderId;
    private String scanDate;


}
