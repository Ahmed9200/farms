package com.example.authentication.requests.userRequests;

import lombok.Data;

import java.util.Date;

@Data
public class FilterUsersByNameLikeRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;

    private String name;

    private String status = "all";
}
