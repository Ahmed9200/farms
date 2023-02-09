package com.example.authentication.services.ordersServices;

import com.example.authentication.models.orders.OrdersAttachments;
import com.example.authentication.models.orders.OrdersServices;
import com.example.authentication.repositories.ordersRepo.OrdersAttachmentsRepository;
import com.example.authentication.repositories.ordersRepo.OrdersRepository;
import com.example.authentication.requests.ordersRequests.AddOrderAttachmentsRequest;
import com.example.authentication.requests.ordersRequests.AddOrderRequest;
import com.example.authentication.requests.userRequests.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttachmentService {

@Autowired
OrdersAttachmentsRepository attachmentsRepository;




    public Map<Object, Object> save(AddOrderAttachmentsRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            for (int i=0 ; i<request.getAttachments().size();i++){
                OrdersAttachments attachments1 = new OrdersAttachments(
                        request.getAttachments().get(i),
                        request.getOwnerId(),
                        request.getOrderId()
                );
                attachmentsRepository.save(attachments1);
            }

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
