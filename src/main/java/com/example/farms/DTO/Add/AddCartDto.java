package com.example.farms.DTO.Add;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddCartDto {
    @NotNull
    private int itemId;
    @NotNull
    private int quantity;

}
