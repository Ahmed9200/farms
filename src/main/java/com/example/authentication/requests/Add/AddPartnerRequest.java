package com.example.authentication.requests.Add;

import lombok.Data;

@Data
public class AddPartnerRequest {
    private String name;
    private String description;
    private String photo;
}
