package com.example.authentication.services.ordersServices;

import com.example.authentication.models.Users;
import com.example.authentication.models.orders.Orders;
import com.example.authentication.models.orders.OrdersServices;
import com.example.authentication.models.orders.OrdersStatus;
import com.example.authentication.repositories.ordersRepo.OrdersRepository;
import com.example.authentication.repositories.ordersRepo.OrdersStatusRepository;
import com.example.authentication.repositories.usersRepo.UsersRepository;
import com.example.authentication.requests.ordersRequests.AddOrderRequest;
import com.example.authentication.requests.ordersRequests.AddStatusRequest;
import com.example.authentication.requests.userRequests.UserRegisterRequest;
import com.example.authentication.services.notificationServices.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatusService {

@Autowired
OrdersStatusRepository statusRepository;

@Autowired
OrdersRepository ordersRepository;
@Autowired
NotificationService notificationService;
@Autowired
UsersRepository usersRepository;




    public Map<Object, Object> save(AddStatusRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

                OrdersStatus status = new OrdersStatus(request.getStatus(), request.getOrderId());
                statusRepository.save(status);
                // update current status of order
            ordersRepository.updateCurrentStatus(request.getOrderId(), request.getStatus());

            //sending notification
            Orders order = ordersRepository.findById(request.getOrderId()).get();
            Users user = usersRepository.findById(order.getOwnerId());

            notificationService.sendNotification(
                    user.getId(),
                    user.getNotificationToken(),
                    Integer.parseInt(status.getOrderStatus()),
                    request.getOrderId()
            );
            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
        }
        return res;
    }


    public Object orderTimeline(int id) {
        Map<Object,Object> res = new HashMap<>();
        try{

            List<OrdersStatus> timeline = statusRepository.orderTimeLine(id);

            //add success status to response map
            res.put("status","success");
            res.put("timeline",timeline);

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
        }
        return res;
    }
}
