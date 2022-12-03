package com.example.authentication.requests.userRequests;

import lombok.Data;

import java.util.Date;

@Data
public class FilterUsersByDateRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;

    private String startDate;
    private String endDate;

}
