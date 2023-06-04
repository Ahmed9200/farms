package com.example.farms.DTO.Update;

import lombok.Data;

@Data
public class UpdatePartnerRequest {
    private int partnerId;
    private String name;
    private String description;
    private String photo;
    private String photoContentType;
}
