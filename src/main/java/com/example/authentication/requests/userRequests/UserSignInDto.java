package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UserSignInDto {

    private String phone;
    private String password;
}
