package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateUserPhotoRequest {

    private String photo;
    private int userId;
}
