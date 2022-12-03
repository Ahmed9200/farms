package com.example.authentication.controllers;

import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.complaintsRequests.AddComplainMessagesRequest;
import com.example.authentication.requests.complaintsRequests.AddComplainRequest;
import com.example.authentication.requests.complaintsRequests.AddComplainResponsesRequest;
import com.example.authentication.requests.complaintsRequests.FilterComplaintsByemailLikeRequest;
import com.example.authentication.requests.userRequests.*;
import com.example.authentication.responses.JwtResponse;
import com.example.authentication.services.ComplaintsService;
import com.example.authentication.services.TokenService;
import com.example.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/complaints")
public class ComplaintsController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ComplaintsService complaintsService;



    @PostMapping(value = "/addComplaint", produces = {"application/json"})
    @ResponseBody
    public Object addComplaint(@RequestBody AddComplainRequest request ,
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

            res = complaintsService.addComplain(request.getEmail(),
                    Integer.parseInt(tokenRes.get("userId").toString()));


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
            res.put("error","authentication faild !! phone or password is wrong");
        }

        return res;
    }


    @PostMapping(value = "/addComplaintMessage", produces = {"application/json"})
    @ResponseBody
    public Object addComplaintMessage(@RequestBody AddComplainMessagesRequest request ,
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

            res = complaintsService.addComplainMessage(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
            res.put("error","authentication faild !! phone or password is wrong");
        }

        return res;
    }


    @PostMapping(value = "/addComplaintResponse", produces = {"application/json"})
    @ResponseBody
    public Object register(@RequestBody AddComplainResponsesRequest request ,
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

            res = complaintsService.addComplainResponse(request);


        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
            res.put("error","authentication faild !! phone or password is wrong");
        }

        return res;
    }








    @GetMapping(value = "/ComplaintsMessages/{complaintId}")
    public Object ComplaintsMessages(@PathVariable("complaintId") int complaintId) {
        return complaintsService.getAllComplaintsMessages(complaintId);
    }

    @GetMapping(value = "/complaintResponses/{complaintId}")
    public Object complaintResponses(@PathVariable("complaintId") int complaintId) {
        return complaintsService.getAllComplaintsResponses(complaintId);
    }







    @PostMapping(value = "/updateComplaintsStatusToClose/{complainId}")
    public Object updateComplaintsStatusToClose(@PathVariable("complainId")int complainId,
                                                @RequestHeader("Authorization") String token) {

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
        return complaintsService.updateStatusToClose(complainId);
    }









    @PostMapping(value = "/filterComplaintByEmailLike")
    public Object filterComplaintByEmailLike(@RequestBody FilterComplaintsByemailLikeRequest request) {

        return complaintsService.getAllUsersFilteredByUserEmailByPagination(request);
    }


    @PostMapping(value = "/allComplaints")
    public Object allComplaints(@RequestBody LimitAndOffsetRequest request) {

        return complaintsService.getAllComplaintsByCreatedDateByPagination(request);
    }



}
