package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UpdateUserNameRequest {

    private String name;
    private int userId;
}
