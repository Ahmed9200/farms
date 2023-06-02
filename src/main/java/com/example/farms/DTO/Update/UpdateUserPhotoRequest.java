package com.example.farms.DTO.Update;

import lombok.Data;

@Data
public class UpdateUserPhotoRequest {

    private String photo;
    private String contentType;
    private int userId;
}
