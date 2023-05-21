package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateUserNameRequest {

    private String name;
    private int userId;
}
