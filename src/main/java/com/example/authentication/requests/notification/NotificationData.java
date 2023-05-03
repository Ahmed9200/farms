package com.example.authentication.requests.notification;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Data
public class NotificationData {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String receiverId;
    private String token;
}