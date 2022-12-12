package com.example.authentication.requests.adminRequests;

import lombok.Data;

@Data
public class FilterAdminUsersByEmailLikeRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;

    private String email;

    private String status = "all";
}
