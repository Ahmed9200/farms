package com.example.authentication.services.ordersServices;

import com.example.authentication.DamhaApplication;
import com.example.authentication.models.Users;
import com.example.authentication.models.orders.Orders;
import com.example.authentication.repositories.configRepo.ConfigRepository;
import com.example.authentication.repositories.ordersRepo.OrdersRepository;
import com.example.authentication.requests.ordersRequests.*;
import com.example.authentication.requests.userRequests.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersService {

@Autowired
OrdersRepository ordersRepository;

@Autowired
ServicesService servicesService;

@Autowired
AttachmentService attachmentService;

@Autowired
StatusService statusService;


// to save order {
// 1- save order data
// 2- save order services data (method from orderServices service)
// 3-save order attachment data (method from attachment service)
//4- set the current status and save it to its own table
// 5- make all steps from 2 to 5 parallel and return result to front
// }
    public Map<Object, Object> saveOrder(AddOrderRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //first save order main data
            Orders order =  new Orders(request);
            order = ordersRepository.save(order);

            //adding services for order
            AddServicesRequest servicesRequest = new AddServicesRequest(
                    request.getOrderServices(),
                    request.getOwnerId(),
                    order.getId()
            );
            Map<Object,Object> servicesRes= servicesService.save(servicesRequest);

            //check if serviced added well
            if (servicesRes.get("status").toString().equals("success"))
                res.put("servicesStatus","success");
            else
                res.put("servicesStatus","error");

            //adding status for order
            AddStatusRequest statusRequest = new AddStatusRequest(order.getOrderCurrentStatus(), order.getId());
            Map<Object,Object> statusRes = statusService.save(statusRequest);

            //check if status of order added well
            if (statusRes.get("status").toString().equals("success"))
                res.put("statusOfOrderStatus","success");
            else
                res.put("statusOfOrderStatus","error");

            //adding order attachments
            AddOrderAttachmentsRequest attachmentsRequest = new AddOrderAttachmentsRequest(
                    request.getOwnerId(),
                    order.getId(),
                    request.getOrderAttachments());

            Map<Object,Object> attachmentRes = attachmentService.save(attachmentsRequest);


            //check if attachment of order added well
            if (attachmentRes.get("status").toString().equals("success"))
                res.put("attachmentStatus","success");
            else
                res.put("attachmentStatus","error");

            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
        }
        return res;
    }

    public Map<Object, Object> updateOrder(EditOrderRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //first save order main data
            Orders order = ordersRepository.findById(request.getOrderId()).get();
            order.setData(request,order);
            order = ordersRepository.save(order);

            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
        }
        return res;
    }




}
