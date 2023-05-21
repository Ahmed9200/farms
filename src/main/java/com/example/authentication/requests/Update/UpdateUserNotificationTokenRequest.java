package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateUserNotificationTokenRequest {

    private String token;
    private int userId;
}
