package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderAttachmentsRequest {
    private int ownerId;
    private int orderId;
    private List<AttachmentData> attachments;

    public AddOrderAttachmentsRequest(int ownerId, int orderId, List<AttachmentData> attachments) {
        this.ownerId = ownerId;
        this.orderId = orderId;
        this.attachments = attachments;
    }
}
