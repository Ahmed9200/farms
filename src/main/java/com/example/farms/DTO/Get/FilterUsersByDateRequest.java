package com.example.farms.DTO.Get;

import lombok.Data;

@Data
public class FilterUsersByDateRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;

    private String startDate;
    private String endDate;

    private String status = "all";

}
