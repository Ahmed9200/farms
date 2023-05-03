package com.example.authentication.requests.userRequests;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegisterRequest {
    private String phone;
    private String password;
    private String token;

}
