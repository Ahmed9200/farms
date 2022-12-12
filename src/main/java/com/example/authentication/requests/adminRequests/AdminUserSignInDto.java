package com.example.authentication.requests.adminRequests;

import lombok.Data;

@Data
public class AdminUserSignInDto {

    private String email;
    private String password;
}
