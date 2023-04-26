package com.example.authentication.services.ordersServices;

import com.example.authentication.models.orders.OrdersServices;
import com.example.authentication.repositories.ordersRepo.OrdersRepository;
import com.example.authentication.repositories.ordersRepo.OrdersServicesRepository;
import com.example.authentication.requests.ordersRequests.AddOrderRequest;
import com.example.authentication.requests.ordersRequests.AddServicesRequest;
import com.example.authentication.requests.ordersRequests.UpdateServicesRequest;
import com.example.authentication.requests.userRequests.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicesService {

@Autowired
OrdersServicesRepository servicesRepository;



    public Map<Object, Object> save(AddServicesRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            for (int i=0 ; i<request.getServices().size();i++){
                OrdersServices service = new OrdersServices(
                        request.getServices().get(i),
                        "",
                        request.getOrderId(),
                        "user",
                        "active"
                );
                servicesRepository.save(service);
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

    public Map<Object, Object> updateServicesFromAdmin(List<UpdateServicesRequest> request){
        Map<Object,Object> res = new HashMap<>();
        try{

            for (int i=0 ; i<request.size();i++){

                if (request.get(i).getServiceId()==0){
                    OrdersServices service = new OrdersServices(
                            request.get(i).getName(),
                            request.get(i).getPrice(),
                            request.get(i).getOrderId(),
                            request.get(i).getAddedBy(),
                            request.get(i).getStatus()
                    );
                    servicesRepository.save(service);
                }else{
                    OrdersServices service = servicesRepository.findById(request.get(i).getServiceId()).get();
                    service.setAddedBy(request.get(i).getAddedBy());
                    service.setStatus(request.get(i).getStatus());
                    service.setOrderService(request.get(i).getName());
                    service.setOrderServicePrice(request.get(i).getPrice());
                    servicesRepository.save(service);
                }


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


    public Map<Object, Object> deleteServicesByOrderId(int id){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update status
            servicesRepository.deleteAllByOrderId(id);

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
