package com.example.authentication.requests.adminRequests;

import lombok.Data;

@Data
public class UpdateAdminRoleRequest {

    private String role;
    private int userId;
}
