package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateUserEmailRequest {

    private String email;
    private int userId;
}
