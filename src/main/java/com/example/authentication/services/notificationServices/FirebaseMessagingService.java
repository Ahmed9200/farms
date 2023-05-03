package com.example.authentication.services.notificationServices;

import com.example.authentication.requests.notification.NotificationData;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public Map<Object, Object> sendNotification(NotificationData notificationData, String token) throws FirebaseMessagingException {

        Map<Object, Object> res = new HashMap<>();

        try {

            Notification notification = Notification
                    .builder()
                    .setTitle(notificationData.getSubject())
                    .setBody(notificationData.getContent())
                    .build();

                Message message = Message
                        .builder()
                        .setToken(token)
                        .setNotification(notification)
                        .putAllData(notificationData.getData())
                        .build();

                String messageId = firebaseMessaging.send(message);

            res.put("status","success" );
            res.put("messageId", messageId);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("error", e.getMessage());
        }

        return res;
    }


    public Map<Object, Object> sendTopicNotification(NotificationData notificationData , String topic) throws FirebaseMessagingException {

        Map<Object, Object> res = new HashMap<>();

        try {

            Notification notification = Notification
                    .builder()
                    .setTitle(notificationData.getSubject())
                    .setBody(notificationData.getContent())
                    .build();


            Message webMessage = Message
                    .builder()
                    .setTopic(topic)
                    .setNotification(notification)
                    .putAllData(notificationData.getData())
                    .build();

            res.put("status","success");
            res.put("messageId", firebaseMessaging.send(webMessage));
        } catch (Exception e) {
            res.put("status","error");
            res.put("error", e.getMessage());
        }

        return res;
    }

}
