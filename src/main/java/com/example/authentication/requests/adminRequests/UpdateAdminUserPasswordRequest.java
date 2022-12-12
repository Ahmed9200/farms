package com.example.authentication.requests.adminRequests;

import lombok.Data;

@Data
public class UpdateAdminUserPasswordRequest {

    private String password;
    private int userId;
    private String email;
}
