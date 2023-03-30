package com.example.authentication.services.ordersServices;

import com.example.authentication.DamhaApplication;
import com.example.authentication.models.Users;
import com.example.authentication.models.orders.Orders;
import com.example.authentication.models.orders.OrdersAttachments;
import com.example.authentication.models.orders.OrdersServices;
import com.example.authentication.repositories.configRepo.ConfigRepository;
import com.example.authentication.repositories.ordersRepo.OrdersAttachmentsRepository;
import com.example.authentication.repositories.ordersRepo.OrdersRepository;
import com.example.authentication.repositories.ordersRepo.OrdersServicesRepository;
import com.example.authentication.repositories.ordersRepo.OrdersStatusRepository;
import com.example.authentication.repositories.usersRepo.UsersRepository;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.complaintsRequests.ComplaintsRequestPagination;
import com.example.authentication.requests.ordersRequests.*;
import com.example.authentication.requests.userRequests.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    OrdersServicesRepository servicesRepository;

    @Autowired
    OrdersStatusRepository statusRepository;

    @Autowired
    OrdersAttachmentsRepository attachmentsRepository;


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
    public Map<Object, Object> saveOrder(AddOrderRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            //first save order main data
            Orders order = new Orders(request);
            order = ordersRepository.save(order);

            res.put("orderId",order.getId());
            //adding services for order
            AddServicesRequest servicesRequest = new AddServicesRequest(
                    request.getOrderServices(),
                    request.getOwnerId(),
                    order.getId()
            );
            Map<Object, Object> servicesRes = servicesService.save(servicesRequest);

            //check if serviced added well
            if (servicesRes.get("status").toString().equals("success"))
                res.put("servicesStatus", "success");
            else
                res.put("servicesStatus", "error");

            //adding status for order
            AddStatusRequest statusRequest = new AddStatusRequest(order.getOrderCurrentStatus(), order.getId());
            Map<Object, Object> statusRes = statusService.save(statusRequest);

            //check if status of order added well
            if (statusRes.get("status").toString().equals("success"))
                res.put("statusOfOrderStatus", "success");
            else
                res.put("statusOfOrderStatus", "error");

            //adding order attachments
            AddOrderAttachmentsRequest attachmentsRequest = new AddOrderAttachmentsRequest(
                    request.getOwnerId(),
                    order.getId(),
                    request.getOrderAttachments());

            Map<Object, Object> attachmentRes = attachmentService.save(attachmentsRequest);


            //check if attachment of order added well
            if (attachmentRes.get("status").toString().equals("success"))
                res.put("attachmentStatus", "success");
            else
                res.put("attachmentStatus", "error");

            //add success status to response map
            res.put("status", "success");

        } catch (Exception e) {
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status", "error");
        }
        return res;
    }

    public Map<Object, Object> updateOrder(EditOrderRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            //first save order main data
            Orders order = ordersRepository.findById(request.getOrderId()).get();
            order.setData(request, order);
            order = ordersRepository.save(order);

            //add success status to response map
            res.put("status", "success");

        } catch (Exception e) {
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status", "error");
        }
        return res;
    }

    public Map<Object, Object> updateScanDate(UpdateScanDateRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            //update scan date
            ordersRepository.updateScanDate(request.getOrderId(), request.getScanDate());

            //add success status to response map
            res.put("status", "success");

        } catch (Exception e) {
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status", "error");
        }
        return res;
    }


    public Map<Object, Object> updateCurrentStatus(String status, int orderId) {
        Map<Object, Object> res = new HashMap<>();
        try {

            //update status
            ordersRepository.updateCurrentStatus(orderId, status);

            //add success status to response map
            res.put("status", "success");

        } catch (Exception e) {
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status", "error");
        }
        return res;
    }


    public Map<Object, Object> deleteOrderById(int id) {
        Map<Object, Object> res = new HashMap<>();
        try {

            //delete order data
            ordersRepository.deleteById(id);

            //delete all attachments of order
            attachmentService.deleteAttachmentsByOrderId(id);

            //delete all services of order
            servicesService.deleteServicesByOrderId(id);

            //add success status to response map
            res.put("status", "success");

        } catch (Exception e) {
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status", "error");
        }
        return res;
    }

    public Object findAllOrdersPagination(LimitAndOffsetRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            List<Orders> orders = ordersRepository.findAllOrders(request.getLimit(), request.getOffset());
            long total = ordersRepository.count();

            res.put("orders", orders);
            res.put("total", total);

            //if any thing goes well add status to success
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;
    }


    public Object findAllOrdersByCreationDatePagination(DateRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            List<Orders> orders = ordersRepository.filterOrdersByCreationDate(
                    request.getLimit(),
                    request.getOffset(),
                    request.getStartDate(),
                    request.getEndDate()
            );

            long total = ordersRepository.filterOrdersByCreationDateCount(
                    request.getLimit(),
                    request.getOffset(),
                    request.getStartDate(),
                    request.getEndDate()
            );

            res.put("orders", orders);
            res.put("total", total);

            //if any thing goes well add status to success
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;
    }


    public Object findAllOrdersByScanDatePagination(DateRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            List<Orders> orders = ordersRepository.filterOrdersByScanDate(
                    request.getLimit(),
                    request.getOffset(),
                    request.getStartDate(),
                    request.getEndDate()
            );

            long total = ordersRepository.filterOrdersByScanDateCount(
                    request.getLimit(),
                    request.getOffset(),
                    request.getStartDate(),
                    request.getEndDate()
            );

            res.put("orders", orders);
            res.put("total", total);

            //if any thing goes well add status to success
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;
    }

    public Object findAllOrdersByTypePagination(TypeRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            List<Orders> orders = ordersRepository.filterOrdersByType(
                    request.getLimit(),
                    request.getOffset(),
                    request.getType()
            );

            long total = ordersRepository.filterOrdersByTypeCount(
                    request.getLimit(),
                    request.getOffset(),
                    request.getType()
            );

            res.put("orders", orders);
            res.put("total", total);

            //if any thing goes well add status to success
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;
    }


    public Object findAllNewOrdersPagination(LimitAndOffsetRequest request) {
        Map<Object, Object> res = new HashMap<>();
        try {

            Date d = new Date();
            List<Orders> orders = ordersRepository.filterOrdersByCreationDate(
                    request.getLimit(),
                    request.getOffset(),
                    new SimpleDateFormat("yyyy-MM-HH").format(d),
                    new SimpleDateFormat("yyyy-MM-HH").format(d)
            );

            long total = ordersRepository.filterOrdersByCreationDateCount(
                    request.getLimit(),
                    request.getOffset(),
                    new SimpleDateFormat("yyyy-MM-HH").format(d),
                    new SimpleDateFormat("yyyy-MM-HH").format(d)
            );

            res.put("orders", orders);
            res.put("total", total);

            //if any thing goes well add status to success
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;
    }

    public Object orderDetails(int id) {

        //getting order details
        //getting services
        //getting attachments
        //getting user data

        Map<Object, Object> res = new HashMap<>();
        try {

            Orders order = ordersRepository.findById(id).get();
            List<OrdersServices> service = servicesRepository.findAllByOrderId(id);
            List<OrdersAttachments> attachments = attachmentsRepository.findAllByOrderId(id);
            Map<Object,Object> user = usersRepository.lightUser(order.getOwnerId());

            Map<Object,Object> orderDetails = new HashMap<>();
            orderDetails.put("order",order);
            orderDetails.put("services",service);
            orderDetails.put("attachments",attachments);
            orderDetails.put("user",user);

            //if any thing goes well add status to success
            res.put("orderDetails",orderDetails);
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;
    }

    public Object filterOrders(FilterOrdersRequest req) {
        Map<Object, Object> res = new HashMap<>();
        try {

            List<Map<Object,Object>> result = new ArrayList<>();
        //check order by
            if (req.isOrderByCreationDate()){
                if (req.isAsc()){
                    result = ordersRepository.filterOrdersOrderByCreationASC(
                            req.getType().isEmpty()?null :req.getType().get(0),
                            req.getStatus().isEmpty()?null:req.getStatus().get(0),
                            req.getOrderId() == 0 ? null : req.getOrderId(),
                            req.getPhone().isEmpty()?null: req.getPhone(),
                            req.getCreationDateStart().isEmpty()?null: req.getCreationDateStart(),
                            req.getCreationDateEnd(),
                            req.getLimit(),
                            req.getOffset(),
                            req.getUserId() == 0 ? null : req.getUserId()
                    );
                    System.out.println("order by creation date asc");
                }else{
                    result = ordersRepository.filterOrdersOrderByCreationDESC(
                            req.getType().isEmpty()?null :req.getType().get(0),
                            req.getStatus().isEmpty()?null:req.getStatus().get(0),
                            req.getOrderId() == 0 ? null : req.getOrderId(),
                            req.getPhone().isEmpty()?null: req.getPhone(),
                            req.getCreationDateStart().isEmpty()?null: req.getCreationDateStart(),
                            req.getCreationDateEnd(),
                            req.getLimit(),
                            req.getOffset(),
                            req.getUserId() == 0 ? null : req.getUserId()
                    );
                    System.out.println("order by creation date desc");
                }
            }


            if (req.isOrderByScanDate()){
                if (req.isAsc()){
                    result = ordersRepository.filterOrdersOrderByScanDateASC(
                            req.getType().isEmpty()?null :req.getType().get(0),
                            req.getStatus().isEmpty()?null:req.getStatus().get(0),
                            req.getOrderId() == 0 ? null : req.getOrderId(),
                            req.getPhone().isEmpty()?null: req.getPhone(),
                            req.getCreationDateStart().isEmpty()?null: req.getCreationDateStart(),
                            req.getCreationDateEnd(),
                            req.getLimit(),
                            req.getOffset(),
                            req.getUserId() == 0 ? null : req.getUserId()
                    );
                    System.out.println("order by scan date asc");
                }else{
                    result = ordersRepository.filterOrdersOrderByScanDateDESC(
                            req.getType().isEmpty()?null :req.getType().get(0),
                            req.getStatus().isEmpty()?null:req.getStatus().get(0),
                            req.getOrderId() == 0 ? null : req.getOrderId(),
                            req.getPhone().isEmpty()?null: req.getPhone(),
                            req.getCreationDateStart().isEmpty()?null: req.getCreationDateStart(),
                            req.getCreationDateEnd(),
                            req.getLimit(),
                            req.getOffset(),
                            req.getUserId() == 0 ? null : req.getUserId()
                    );
                    System.out.println("order by scan date desc");
                }
            }

            // if there is no order by call no order by select

            if (!req.isOrderByScanDate()&& !req.isOrderByCreationDate()){
                result = ordersRepository.filterOrders(
                        req.getType().isEmpty()?null :req.getType().get(0),
                        req.getStatus().isEmpty()?null:req.getStatus().get(0),
                        req.getOrderId() == 0 ? null : req.getOrderId(),
                        req.getPhone().isEmpty()?null: req.getPhone(),
                        req.getCreationDateStart().isEmpty()?null: req.getCreationDateStart(),
                        req.getCreationDateEnd(),
                        req.getLimit(),
                        req.getOffset(),
                        req.getUserId() == 0 ? null : req.getUserId()
                );
                System.out.println("filter without order by");
            }



            // getting total of result
            long total = ordersRepository.filterOrdersCount(
                    req.getType().isEmpty()?null :req.getType().get(0),
                    req.getStatus().isEmpty()?null:req.getStatus().get(0),
                    req.getOrderId() == 0 ? null : req.getOrderId(),
                    req.getPhone().isEmpty()?null: req.getPhone(),
                    req.getCreationDateStart().isEmpty()?null: req.getCreationDateStart(),
                    req.getCreationDateEnd(),
                    req.getUserId() == 0 ? null : req.getUserId()
            );

            //if any thing goes well add status to success
            res.put("result", result);
            res.put("total",total);
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;
    }

    public Object ordersSummary(int id) {


        Map<Object, Object> res = new HashMap<>();
        try {

            //getting total of new orders and working orders and finished orders
            res.put("new",ordersRepository.totalNew(id));
            res.put("working",ordersRepository.totalWorking(id));
            res.put("finished",ordersRepository.totalFinished(id));
            res.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status", "error");
            res.put("error", e.getMessage());
        }
        return res;

    }
}