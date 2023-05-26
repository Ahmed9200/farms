package com.example.farms.DTO.Update;

import lombok.Data;

@Data
public class UpdateExpYearRequest {
    private int expYearId;
    private String year;
    private String description;
}
