package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UpdateUserAdditionalPhoneRequest {

    private String additionalPhone;
    private int userId;
}
