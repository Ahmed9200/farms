package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderAttachmentsRequest {
    private int ownerId;
    private int orderId;
    private List<String> attachments;

    public AddOrderAttachmentsRequest(int ownerId, int orderId, List<String> attachments) {
        this.ownerId = ownerId;
        this.orderId = orderId;
        this.attachments = attachments;
    }
}
