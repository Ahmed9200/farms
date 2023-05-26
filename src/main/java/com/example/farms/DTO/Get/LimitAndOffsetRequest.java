package com.example.farms.DTO.Get;

import lombok.Data;

@Data
public class LimitAndOffsetRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;
    private String status = "all";
}
