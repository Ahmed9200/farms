package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateBannerRequest {
    private int bannerId;
    private String description1;
    private String description2;
    private int priority;
    private String photo;

}
