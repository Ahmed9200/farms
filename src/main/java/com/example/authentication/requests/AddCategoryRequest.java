package com.example.authentication.requests;

import lombok.Data;

@Data
public class AddCategoryRequest {
    private String name;
    private String description;
    private String photo;

}
