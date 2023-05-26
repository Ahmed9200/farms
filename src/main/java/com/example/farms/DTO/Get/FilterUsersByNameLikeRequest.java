package com.example.farms.DTO.Get;

import lombok.Data;

@Data
public class FilterUsersByNameLikeRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;

    private String name;

    private String status = "all";
}
