package com.example.farms.DTO.Add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddInvoiceDto {
    @NotNull
    private List<AddCartDto> items;
    @NotBlank
    private String username;
    @NotBlank
    private String phone1;
    private String phone2;
    @NotBlank
    private String address;
    @NotNull
    private double total;

    @NotNull
    private double vat;

    @NotNull
    private double itemsPrice;
}
