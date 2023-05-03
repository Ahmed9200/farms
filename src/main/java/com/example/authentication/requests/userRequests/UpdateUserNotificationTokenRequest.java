package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UpdateUserNotificationTokenRequest {

    private String token;
    private int userId;
}
