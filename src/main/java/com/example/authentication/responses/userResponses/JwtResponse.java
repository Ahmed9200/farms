package com.example.authentication.responses.userResponses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JwtResponse {

    private String token;
    private Date expirationDate;

    public JwtResponse(String token , Date date) {
        this.token = token;
        expirationDate=date;
    }

    public String getExpirationDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expirationDate);
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}