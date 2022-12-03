package com.example.authentication.requests.complaintsRequests;

import lombok.Data;

@Data
public class AddComplainMessagesRequest {
    private String message;
    private int complainId;
}
