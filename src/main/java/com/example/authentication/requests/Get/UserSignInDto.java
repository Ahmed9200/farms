package com.example.authentication.requests.Get;

import lombok.Data;

@Data
public class UserSignInDto {

    private String phone;
    private String password;
}
