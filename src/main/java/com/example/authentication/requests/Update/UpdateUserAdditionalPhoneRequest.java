package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateUserAdditionalPhoneRequest {

    private String additionalPhone;
    private int userId;
}
