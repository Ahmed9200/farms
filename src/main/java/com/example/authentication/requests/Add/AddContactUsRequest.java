package com.example.authentication.requests.Add;

import lombok.Data;

@Data
public class AddContactUsRequest {
    private String username;
    private String userPhone;
    private String userEmail;
    private String message;

}
