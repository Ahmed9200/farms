package com.example.farms.DTO.Update;

import lombok.Data;

@Data
public class UpdateUserAdditionalPhoneRequest {

    private String additionalPhone;
    private int userId;
}
