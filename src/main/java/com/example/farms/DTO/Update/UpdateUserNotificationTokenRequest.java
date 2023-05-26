package com.example.farms.DTO.Update;

import lombok.Data;

@Data
public class UpdateUserNotificationTokenRequest {

    private String token;
    private int userId;
}
