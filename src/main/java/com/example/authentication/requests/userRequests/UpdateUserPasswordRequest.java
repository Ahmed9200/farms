package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UpdateUserPasswordRequest {

    private String password;
    private int userId;
    private String phone;
}
