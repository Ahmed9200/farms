package com.example.authentication.requests.userRequests;

import lombok.Data;

@Data
public class UpdateUserPhotoRequest {

    private String photo;
    private int userId;
}
