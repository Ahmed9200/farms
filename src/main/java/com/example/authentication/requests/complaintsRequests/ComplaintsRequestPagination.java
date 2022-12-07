package com.example.authentication.requests.complaintsRequests;

import lombok.Data;

@Data
public class ComplaintsRequestPagination {
    private int limit = Integer.MAX_VALUE;
    private int offset = 0;
    private int complaintId;
    private int complaintMessageId;
}
