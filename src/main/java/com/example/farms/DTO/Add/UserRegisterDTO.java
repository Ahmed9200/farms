package com.example.farms.DTO.Add;

import com.example.farms.models.enums.UserRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterDTO {
    private String phone;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotNull
    private UserRole role;
    private String photo;

}
