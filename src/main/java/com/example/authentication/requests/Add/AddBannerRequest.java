package com.example.authentication.requests.Add;

import lombok.Data;

@Data
public class AddBannerRequest {
    private String description2;
    private String description1;
    private String photo;
    private int priority=2;

}
