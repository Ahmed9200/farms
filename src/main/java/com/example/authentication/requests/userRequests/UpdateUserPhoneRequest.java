package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UpdateUserPhoneRequest {

    private String phone;
    private int userId;
}
