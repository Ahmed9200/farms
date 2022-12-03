package com.example.authentication.requests.complaintsRequests;

import lombok.Data;

@Data
public class FilterComplaintsByemailLikeRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;

    private String email;

}
