package com.example.authentication.services.ordersServices;

import com.example.authentication.models.Users;
import com.example.authentication.models.orders.Orders;
import com.example.authentication.models.orders.OrdersReports;
import com.example.authentication.models.orders.OrdersStatus;
import com.example.authentication.repositories.ordersRepo.OrdersReportsRepository;
import com.example.authentication.repositories.ordersRepo.OrdersRepository;
import com.example.authentication.repositories.ordersRepo.OrdersStatusRepository;
import com.example.authentication.repositories.usersRepo.UsersRepository;
import com.example.authentication.requests.ordersRequests.AddReportRequest;
import com.example.authentication.requests.ordersRequests.AddStatusRequest;
import com.example.authentication.requests.ordersRequests.EditOrderRequest;
import com.example.authentication.requests.ordersRequests.UpdateReportRequest;
import com.example.authentication.services.notificationServices.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportsService {

@Autowired
OrdersReportsRepository reportsRepository;

@Autowired
OrdersRepository ordersRepository;

@Autowired
StatusService statusService;

    @Autowired
    NotificationService notificationService;
    @Autowired
    UsersRepository usersRepository;



    public Map<Object, Object> save(AddReportRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

                OrdersReports reports = new OrdersReports(request.getReport(), request.getOrderId());
                reportsRepository.save(reports);

                //add new status to status table
            statusService.save(new AddStatusRequest("3", reports.getOrderId()));//report upload status = 3

                // update current status of order
            ordersRepository.updateCurrentStatus(request.getOrderId(),"3");

            //add success status to response map
            res.put("status","success");
            res.put("report",reports);

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
        }
        return res;
    }



    public Map<Object, Object> updateReport(UpdateReportRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //first save order main data
            OrdersReports order = reportsRepository.findById(request.getReportId()).get();
            order.setOrderReport(request.getReport());

            order = reportsRepository.save(order);

            Orders o = ordersRepository.findById(order.getOrderId()).get();
            ordersRepository.updateCurrentStatus(o.getId(),"3");

            //sending notification

            Users user = usersRepository.findById(o.getOwnerId());

            notificationService.sendNotification(
                    user.getId(),
                    user.getNotificationToken(),
                    3,
                    o.getId(),
                    Integer.parseInt(o.getOrderType())
            );

            //add success status to response map
            res.put("status","success");
            res.put("report",order);

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
        }
        return res;
    }

    public Object findOrderReport(int orderId) {
        Map<Object,Object> res = new HashMap<>();
        try{

            OrdersReports reports = reportsRepository.findByOrderId(orderId).get(0);

            //add success status to response map
            res.put("status","success");
            res.put("report",reports);

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
        }
        return res;
    }
}
