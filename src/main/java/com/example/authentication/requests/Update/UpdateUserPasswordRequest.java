package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateUserPasswordRequest {

    private String password;
    private int userId;
    private String phone;
}
