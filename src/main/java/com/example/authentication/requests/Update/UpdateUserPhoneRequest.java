package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateUserPhoneRequest {

    private String phone;
    private int userId;
}
