package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderRequest {
    //order main data
    private String orderType;
    private String carType;
    private String carModel;
    private int ownerId;
    private String locationLat;
    private String locationLng;
    private String description;
    private String executionTime;
    //order services data
    private List<String> orderServices;
    //order attachments data
    private List<AttachmentData> orderAttachments;

}
