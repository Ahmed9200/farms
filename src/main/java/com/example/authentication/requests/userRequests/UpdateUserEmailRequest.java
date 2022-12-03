package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UpdateUserEmailRequest {

    private String email;
    private int userId;
}
