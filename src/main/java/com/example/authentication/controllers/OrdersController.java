package com.example.authentication.controllers;

import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.complaintsRequests.*;
import com.example.authentication.requests.ordersRequests.*;
import com.example.authentication.services.ComplaintsService;
import com.example.authentication.services.TokenService;
import com.example.authentication.services.ordersServices.AttachmentService;
import com.example.authentication.services.ordersServices.OrdersService;
import com.example.authentication.services.ordersServices.ServicesService;
import com.example.authentication.services.ordersServices.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private StatusService statusService;




    @PostMapping(value = "/saveOrder", produces = {"application/json"})
    @ResponseBody
    public Object addComplaint(@RequestBody AddOrderRequest request ,
                           @RequestHeader("Authorization") String token) {
        Map<Object,Object> res = new HashMap<>();
        Map<Object, Object> errorMsg = new HashMap<>();

        //getting token result with this data from the token front sent
        Map<Object,Object> tokenRes = tokenService.isTokenValid(token);

        //check if token valid
        if (((boolean)tokenRes.get("isValid")==false)) {
            //if token not valid make error user not authorized and status with error
            errorMsg.put("status","error");
            errorMsg.put("error", "USER NOT Authorized , token not valid");

            return errorMsg;
        }

        try {

            res = ordersService.saveOrder(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
            res.put("error","authentication faild !! ");
        }

        return res;
    }


    @PostMapping(value = "/saveAttachments", produces = {"application/json"})
    @ResponseBody
    public Object addComplaintMessage(@RequestBody AddOrderAttachmentsRequest request ,
                           @RequestHeader("Authorization") String token) {
        Map<Object,Object> res = new HashMap<>();
        Map<Object, Object> errorMsg = new HashMap<>();

        //getting token result with this data from the token front sent
        Map<Object,Object> tokenRes = tokenService.isTokenValid(token);

        //check if token valid
        if (((boolean)tokenRes.get("isValid")==false)) {
            //if token not valid make error user not authorized and status with error
            errorMsg.put("status","error");
            errorMsg.put("error", "USER NOT Authorized , token not valid");

            return errorMsg;
        }

        try {

            res = attachmentService.save(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }


    @PostMapping(value = "/saveServices", produces = {"application/json"})
    @ResponseBody
    public Object register(@RequestBody AddServicesRequest request ,
                           @RequestHeader("Authorization") String token) {
        Map<Object,Object> res = new HashMap<>();
        Map<Object, Object> errorMsg = new HashMap<>();

        //getting token result with this data from the token front sent
        Map<Object,Object> tokenRes = tokenService.isTokenValid(token);

        //check if token valid
        if (((boolean)tokenRes.get("isValid")==false)) {
            //if token not valid make error user not authorized and status with error
            errorMsg.put("status","error");
            errorMsg.put("error", "USER NOT Authorized , token not valid");

            return errorMsg;
        }

        try {

            res = servicesService.save(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }



    @PostMapping(value = "/saveStatus", produces = {"application/json"})
    @ResponseBody
    public Object register(@RequestBody AddStatusRequest request ,
                           @RequestHeader("Authorization") String token) {
        Map<Object,Object> res = new HashMap<>();
        Map<Object, Object> errorMsg = new HashMap<>();

        //getting token result with this data from the token front sent
        Map<Object,Object> tokenRes = tokenService.isTokenValid(token);

        //check if token valid
        if (((boolean)tokenRes.get("isValid")==false)) {
            //if token not valid make error user not authorized and status with error
            errorMsg.put("status","error");
            errorMsg.put("error", "USER NOT Authorized , token not valid");

            return errorMsg;
        }

        try {

            res = statusService.save(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }




    @PostMapping(value = "/updateOrder", produces = {"application/json"})
    @ResponseBody
    public Object updateOrder(@RequestBody EditOrderRequest request ,
                           @RequestHeader("Authorization") String token) {
        Map<Object,Object> res = new HashMap<>();
        Map<Object, Object> errorMsg = new HashMap<>();

        //getting token result with this data from the token front sent
        Map<Object,Object> tokenRes = tokenService.isTokenValid(token);

        //check if token valid
        if (((boolean)tokenRes.get("isValid")==false)) {
            //if token not valid make error user not authorized and status with error
            errorMsg.put("status","error");
            errorMsg.put("error", "USER NOT Authorized , token not valid");

            return errorMsg;
        }

        try {

            res = ordersService.updateOrder(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }





//    @PostMapping(value = "/ComplaintsMessages")
//    public Object ComplaintsMessages(@RequestBody ComplaintsRequestPagination req) {
//        return complaintsService.getAllComplaintsMessages(req);
//    }
//
//    @PostMapping(value = "/complaintResponsesByCompliantId")
//    public Object complaintResponsesByCompliantId(@RequestBody ComplaintsRequestPagination req) {
//        return complaintsService.getAllComplaintsResponsesByComplainIdOnly(req);
//    }
//
//
//    @PostMapping(value = "/complaintResponses")
//    public Object complaintResponses(@RequestBody ComplaintsRequestPagination req) {
//        return complaintsService.getAllComplaintsResponses(req);
//    }
//
//
//
//
//
//
//    @PostMapping(value = "/updateComplaintsStatusToClose/{complaintId}")
//    public Object updateComplaintsStatusToClose(@PathVariable("complaintId")int complaintId,
//                                                @RequestHeader("Authorization") String token) {
//
//        Map<Object, Object> errorMsg = new HashMap<>();
//
//        //getting token result with this data from the token front sent
//        Map<Object,Object> tokenRes = tokenService.isTokenValid(token);
//
//        //check if token valid
//        if (((boolean)tokenRes.get("isValid")==false)) {
//            //if token not valid make error user not authorized and status with error
//            errorMsg.put("status","error");
//            errorMsg.put("error", "USER NOT Authorized , token not valid");
//
//            return errorMsg;
//        }
//        return complaintsService.updateStatusToClose(complaintId);
//    }
//
//
//
//
//
//
//
//
//
//    @PostMapping(value = "/filterComplaintByEmailLike")
//    public Object filterComplaintByEmailLike(@RequestBody FilterComplaintsByemailLikeRequest request) {
//
//        return complaintsService.getAllUsersFilteredByUserEmailByPagination(request);
//    }
//
//
//    @PostMapping(value = "/allComplaints")
//    public Object allComplaints(@RequestBody LimitAndOffsetRequest request) {
//
//        return complaintsService.getAllComplaintsByCreatedDateByPagination(request);
//    }
//


}
