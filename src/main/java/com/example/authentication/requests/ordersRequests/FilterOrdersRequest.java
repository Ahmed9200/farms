package com.example.authentication.requests.ordersRequests;

import lombok.Data;

import java.util.List;

@Data
public class FilterOrdersRequest {
    private int limit = Integer.MAX_VALUE;
    private int offset =0;
    private int userId =0;
    private boolean orderByCreationDate = false;
    private boolean orderByScanDate = false;
    private boolean asc = false;
    private boolean desc = false;

    private List<String> type=null;
    private List<String> status=null;
    private String phone= "";
    private String creationDateStart= "";
    private String creationDateEnd= "";

    private int orderId=0;
}
