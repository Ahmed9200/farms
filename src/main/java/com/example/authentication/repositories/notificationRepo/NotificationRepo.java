package com.example.authentication.repositories.notificationRepo;

import com.example.authentication.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update notification set seen='true'" +
            " where id in ?1 ;",nativeQuery = true)
    int makeNotificationsSeen(List<Object> ids);

    @Modifying
    @Transactional
    @Query(value = "update notification set seen='true'" +
            " where (seen ='false' or seen = '' or seen = null) and receiver_id=?1 ;",nativeQuery = true)
    int makeAllNotificationsSeen(int userId);


    @Transactional
    @Query(value =
            "select *  from notification where receiver_id=?1 " +
                    "and receiver_id <> notification.sender_id" +
                    " order by date desc limit ?2 offset ?3 ;"
            , nativeQuery = true)
    List<Notification> notificationWithPagination(int userId , int limit , int offset);

    @Transactional
    @Query(value =
            "select count(seen) as 'seenCount' , count(is_read) as 'readCount' from notification " +
                    "where seen = 'false' and receiver_id = ?1 " +
                    "and receiver_id <> notification.sender_id"
            , nativeQuery = true)
    Map<Object,Object>seenCount(int userId);


    @Transactional
    @Query(value =
            "select count(seen) as 'seenCount' , count(is_read) as 'readCount' from notification " +
                    "where is_read = 'false' and receiver_id = ?1 and receiver_id <> notification.sender_id"
            , nativeQuery = true)
    Map<Object,Object>readCount(int userId);




    @Query(value = "select count(id) from notification where " +
            "(?1 is null or action like ?1) and " +
            "(?2 is null or receiver_id=?2) and " +
            "(?3 is null or sender_id=?3) and " +
            "(?4 is null or seen=?4) and " +
            "(?5 is null or is_read=?5) " +
            "and receiver_id <> notification.sender_id" +
            "order by date desc " +
            "limit ?6 offset ?7 ;",nativeQuery = true)
    long countFilterNotifications(String action , String receiverId , String senderId ,
                                           String seen , String read , int limit , int offset);


    @Query(value = "select * from notification where " +
            "(?1 is null or action like ?1) and " +
            "(?2 is null or receiver_id=?2) and " +
            "(?3 is null or sender_id=?3) and " +
            "(?4 is null or seen=?4) and " +
            "(?5 is null or is_read=?5) " +
            "and receiver_id <> notification.sender_id" +
            "order by date desc " +
            "limit ?6 offset ?7 ;",nativeQuery = true)
    List<Notification> filterNotifications(String action , String receiverId , String senderId ,
                                           String seen , String read , int limit , int offset);

}
