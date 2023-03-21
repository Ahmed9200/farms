package com.example.authentication.controllers;

import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.complaintsRequests.*;
import com.example.authentication.requests.ordersRequests.*;
import com.example.authentication.services.ComplaintsService;
import com.example.authentication.services.TokenService;
import com.example.authentication.services.ordersServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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

    @Autowired
    private ReportsService reportsService;


    @PostMapping(value = "/saveOrder", produces = {"application/json"})
    @ResponseBody
    public Object saveOrder(@RequestBody AddOrderRequest request ,
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
    public Object saveAttachments(@RequestBody AddOrderAttachmentsRequest request ,
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
    public Object saveServices(@RequestBody AddServicesRequest request ,
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
    public Object saveStatus(@RequestBody AddStatusRequest request ,
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



    @PostMapping(value = "/saveReport", produces = {"application/json"})
    @ResponseBody
    public Object saveReport(@RequestBody AddReportRequest request ,
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

            res = reportsService.save(request);


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


    @PostMapping(value = "/updateReport", produces = {"application/json"})
    @ResponseBody
    public Object updateReport(@RequestBody UpdateReportRequest request ,
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

            res = reportsService.updateReport(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }



    @PostMapping(value = "/updateScanDateOfOrder", produces = {"application/json"})
    @ResponseBody
    public Object updateScanDateOfOrder(@RequestBody UpdateScanDateRequest request ,
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

            res = ordersService.updateScanDate(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }


    @PostMapping(value = "/deleteOrderById/{id}", produces = {"application/json"})
    @ResponseBody
    public Object deleteOrderById(@PathVariable("id") int id ,
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

            res = ordersService.deleteOrderById(id);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }


    @PostMapping(value = "/deleteAttachmentsByOrderId/{orderId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteAttachmentsByOrderId(@PathVariable("orderId") int id ,
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

            res = attachmentService.deleteAttachmentsByOrderId(id);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }




    @PostMapping(value = "/deleteServicesByOrderId/{orderId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteServicesByOrderId(@PathVariable("orderId") int id ,
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

            res = servicesService.deleteServicesByOrderId(id);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
        }

        return res;
    }



    @PostMapping(value = "/orders")
    public Object orders(@RequestBody LimitAndOffsetRequest req) {
        return ordersService.findAllOrdersPagination(req);
    }

    @PostMapping(value = "/ordersByCreationDate")
    public Object ordersByCreationDate(@RequestBody DateRequest req) {
        return ordersService.findAllOrdersByCreationDatePagination(req);
    }


    @PostMapping(value = "/ordersByScanDate")
    public Object ordersByScanDate(@RequestBody DateRequest req) {
        return ordersService.findAllOrdersByScanDatePagination(req);
    }


    @PostMapping(value = "/ordersByType")
    public Object ordersByType(@RequestBody TypeRequest req) {
        return ordersService.findAllOrdersByTypePagination(req);
    }


    @PostMapping(value = "/newOrders")
    public Object newOrders(@RequestBody LimitAndOffsetRequest req) {
        return ordersService.findAllNewOrdersPagination(req);
    }

    @GetMapping(value = "/orderTimeline/{id}")
    public Object orderTimeline(@PathVariable("id") int id) {
        return statusService.orderTimeline(id);
    }


    @GetMapping(value = "/orderReport/{orderId}")
    public Object orderReport(@PathVariable("orderId") int id) {
        return reportsService.findOrderReport(id);
    }


    @GetMapping(value = "/orderDetails/{orderId}")
    public Object orderDetails(@PathVariable("orderId") int id) {
        return ordersService.orderDetails(id);
    }

    @PostMapping(value = "/filterOrders")
    public Object filterOrders(@RequestBody FilterOrdersRequest req) {
        return ordersService.filterOrders(req);
    }

}
