package com.example.authentication.requests.Update;

import lombok.Data;

@Data
public class UpdateContactUsRequest {
    private int contactId;
    private String username;
    private String userPhone;
    private String userEmail;
    private String message;

}
