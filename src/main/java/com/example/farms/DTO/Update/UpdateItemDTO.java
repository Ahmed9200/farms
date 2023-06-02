package com.example.farms.DTO.Update;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateItemDTO {
    @NotNull
    private int itemId;
    @NotBlank
    private String name;
    @NotNull
    private double price;
    @NotNull
    private int quantity;
    private int sale=0;
    private boolean newItem = false;
    @NotNull
    private int categoryId;
    private String photo;
    private String contentType;
}
