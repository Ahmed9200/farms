package com.example.authentication.models;

import com.example.authentication.requests.notification.NotificationData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "receiver_id")
    private int receiverId;

    @Column(name = "date", columnDefinition = "DateTime")
    private String date;

    @Column(name = "seen")
    private String seen;

    @Column(name = "is_read" , columnDefinition = "varchar(255) default 'false'")
    private String read;


    public Notification(NotificationData data) {
        this.setSubject(data.getSubject());
        this.setContent(data.getContent());
        this.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        this.setReceiverId(Integer.parseInt(data.getReceiverId()));
        this.setSeen("false");
        this.setRead("false");

    }

}
