package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateCategoryRequest {
    private int categoryId;
    private String name;
    private String description;
    private String photo;

}
