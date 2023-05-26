package com.example.farms.DTO.Update;

import lombok.Data;

@Data
public class UpdateUserEmailRequest {

    private String email;
    private int userId;
}
