package com.example.authentication.services.ordersServices;

import com.example.authentication.models.orders.OrdersStatus;
import com.example.authentication.models.orders.OrdersTiming;
import com.example.authentication.repositories.ordersRepo.OrdersRepository;
import com.example.authentication.repositories.ordersRepo.OrdersStatusRepository;
import com.example.authentication.repositories.ordersRepo.OrdersTimingRepository;
import com.example.authentication.requests.ordersRequests.AddOrderTimingRequest;
import com.example.authentication.requests.ordersRequests.AddStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimingService {

@Autowired
OrdersStatusRepository statusRepository;

@Autowired
    OrdersTimingRepository repository;


    public Map<Object, Object> save(List<AddOrderTimingRequest> request){
        Map<Object,Object> res = new HashMap<>();
        try{

            for (int i=0;i< request.size();i++){
                OrdersTiming timing = new OrdersTiming(request.get(i));
                repository.save(timing);
            }

                res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

}