package com.example.authentication.services.notificationServices;

import com.example.authentication.models.Notification;
import com.example.authentication.repositories.notificationRepo.NotificationRepo;
import com.example.authentication.requests.notification.NotificationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @Autowired
    RestTemplate restTemplate;


    public Notification saveNotification(NotificationData data) {
        Map<Object, Object> res = new HashMap<>();
        try {

            Notification notification = new Notification(data);
            notification = notificationRepo.save(notification);
            return notification;
        } catch (Exception e) {
            return null;
        }
    }

    public Map<Object, Object> sendNotification(int receiverId , String token , int status , int orderId) {

        Map<Object, Object> res = new HashMap<>();
        try {
            NotificationData notificationData = new NotificationData();
            String statusName = getStatusNameFromCode(status);
            String orderName = getOrderNameFromCode(orderId);

            notificationData.setContent("تم تحديث حاله طلب "+orderName+" رقم "+orderId+" الي");
            notificationData.setSubject(statusName);
            notificationData.setReceiverId(receiverId+"");
            notificationData.setToken(token);

            Map<String,String> data = new HashMap<>();
            data.put("status",status+"");
            data.put("orderId",orderId+"");
            notificationData.setData(data);

            saveNotification(notificationData);

                return firebaseMessagingService.sendNotification(
                        notificationData,
                        token
                );

        } catch (Exception e) {
            e.printStackTrace();
            res.put("status","error");
            res.put("error", "error when getting message id in notification service , " + e.getMessage());
        }
        return res;

    }

    public String getOrderNameFromCode(int orderId){
       String orderName;
        switch (orderId){
            case 1 :orderName="صيانه سريعه"; break;
            case 2 :orderName="حوادث المركبات"; break;
            case 3 :orderName="تقدير مركبه"; break;
            case 4 :orderName="رفع مطالبه"; break;
            case 5 :orderName="فحص دوري"; break;
            case 6 :orderName="فحص مركبه"; break;
            case 7 :orderName="تعديل مركبه"; break;
            default: orderName="طلب غير موجود"; break;
        }
        return orderName;
    }
    public String getStatusNameFromCode(int status){
        String statusName;
        switch (status){
            case 1 :statusName="استلمت دعمه الطلب"; break;
            case 2 :statusName="استلمت دعمه المركبه"; break;
            case 3 :statusName="تم رفع تقرير الطلب"; break;
            case 4 :statusName="المركبه قيد الصيانه"; break;
            case 5 :statusName="تم صيانه المركبه"; break;
            case 6 :statusName="المركبه في الطريق اليك"; break;
            case 7 :statusName="تم تسليم المركبه اليك"; break;
            case 8 :statusName="المركبه في الطريق الي تقديرات"; break;
            case 9 :statusName="تم رفع المطالبه"; break;
            case 10 :statusName="تم مراجعه الطلب"; break;
            case 11 :statusName="استلمت رساله نصيه من جهه التقدير"; break;
            case 12 :statusName="قيد المراجعه"; break;
            case 13 :statusName="قيد التنفيذ"; break;
            case 14 :statusName="تم تنفيذ الطلب"; break;
            case 15 :statusName="الفحص الدوري قيد التنفيذ"; break;
            case 16 :statusName="تم اجتياز الفحص الدوري"; break;
            case 17 :statusName="لم يتم اجتياز الفحص الدوري"; break;
            case 18 :statusName="تم الفحص"; break;
            case 19 :statusName="جاري رفع تقرير الفحص"; break;
            case 20 :statusName="جاري رفع تقرير المهام"; break;

            default: statusName="حالة غير محدثه"; break;
        }

        return statusName;
    }


    public Map<Object, Object> sendTopicNotification(NotificationData notificationData,String topic) {

        Map<Object, Object> res = new HashMap<>();
        try {
//            if (saveNotification(notificationData).containsKey("success"))
                return firebaseMessagingService.sendTopicNotification(
                        notificationData,
                        topic
                );
//            else
//                res.put("error", "error when saving notification data in DB");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("error", "error when getting message id in notification service , " + e.getMessage());
        }
        return res;

    }

    public Map<Object, Object> makeNotificationRead(int notificationId) {
        try{
            Map<Object,Object> res = new HashMap<>();

            //getting notification
            Notification n = notificationRepo.findById(notificationId).get();
            n.setRead("true");
            notificationRepo.save(n);
            res.put("success","read status updated successfully, changed to read (true)");

            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Map<Object, Object> makeNotificationSeen(List<Object> notificationId) {
        Map<Object,Object> res = new HashMap<>();
        try{
            int isUpdated = notificationRepo.makeNotificationsSeen(notificationId);
            res.put("success","seen status updated successfully, changed to seen (true)");
        }catch (Exception e){
            e.printStackTrace();
            res.put("error","error when updating notification to seen status");
        }
        return res;
    }

    public Map<Object, Object> makeAllNotificationsSeen(int receiverId) {
        Map<Object,Object> res = new HashMap<>();
        try{

            notificationRepo.makeAllNotificationsSeen(receiverId);
            res.put("status","success");
            return res;
        }catch (Exception e){
            res.put("status","error");
            res.put("error","error happen in transfer to seen !! : "+e.getMessage());
            return res;
        }


    }

    public Map<Object, Object> count(int userId) {
        Map<Object,Object> res = new HashMap<>();
        try{

            Map<Object,Object> s = notificationRepo.seenCount(userId);
            Map<Object,Object> r  = notificationRepo.readCount(userId);
            res.put("status","success");
            res.put("remainingReadCount",r.get("readCount"));
            res.put("remainingSeenCount",s.get("seenCount"));
            return res;
        }catch (Exception e){
            res.put("status","error");
            res.put("error","error happen in getting count : "+e.getMessage());
            return res;
        }


    }

}
