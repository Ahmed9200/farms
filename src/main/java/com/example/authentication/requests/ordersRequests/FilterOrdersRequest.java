package com.example.authentication.requests.ordersRequests;

import lombok.Data;

@Data
public class FilterOrdersRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;
    private boolean orderByCreationDate = false;
    private boolean orderByScanDate = false;
    private boolean asc = false;
    private boolean desc = false;

    private String type;
    private String status;
    private String phone;
    private String creationDateStart;
    private String creationDateEnd;

    private int orderId;
}
