package com.example.authentication.requests.adminRequests;

import lombok.Data;

@Data
public class AdminUserRegisterRequest {
    private String email;
    private String password;

}
