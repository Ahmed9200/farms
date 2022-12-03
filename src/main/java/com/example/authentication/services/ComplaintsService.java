package com.example.authentication.services;

import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.models.complaintsModels.Complaints;
import com.example.authentication.models.complaintsModels.ComplaintsMessages;
import com.example.authentication.models.complaintsModels.ComplaintsResponses;
import com.example.authentication.repositories.complaintsRepo.ComplaintsMessagesRepository;
import com.example.authentication.repositories.complaintsRepo.ComplaintsRepository;
import com.example.authentication.repositories.complaintsRepo.ComplaintsResponsesRepository;
import com.example.authentication.repositories.usersRepo.UsersRepository;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.complaintsRequests.AddComplainMessagesRequest;
import com.example.authentication.requests.complaintsRequests.AddComplainResponsesRequest;
import com.example.authentication.requests.complaintsRequests.FilterComplaintsByemailLikeRequest;
import com.example.authentication.requests.userRequests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ComplaintsService {

    @Autowired
    ComplaintsRepository complaintsRepository;
    @Autowired
    ComplaintsMessagesRepository complaintsMessagesRepository;
    @Autowired
    ComplaintsResponsesRepository complaintsResponsesRepository;


    public Map<Object, Object> addComplain(String email , int userId){
        Map<Object,Object> res = new HashMap<>();
        try{

            //create complain object to add data to it
            Complaints complain = new Complaints(userId,email );

            //save complain data in database
            complain = complaintsRepository.save(complain);

            //add complain object to result map
            res.put("complaint",complain);

            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Map<Object, Object> addComplainMessage(AddComplainMessagesRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //create complain message object to add data to it
            ComplaintsMessages complainMessage = new ComplaintsMessages(request.getComplainId(), request.getMessage() );

            //save message complianed data in database
            complainMessage = complaintsMessagesRepository.save(complainMessage);

            //change the last change date for the complaint
            updateLastChangeDateOfComplain(complainMessage.getComplainId());

            //add user object to result map
            res.put("complaintMessage",complainMessage);

            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Map<Object, Object> addComplainResponse(AddComplainResponsesRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //create complain response object to add data to it
            ComplaintsResponses complainResponse = new ComplaintsResponses(request.getComplainId(), request.getResponse() );

            //save response complianed data in database
            complainResponse = complaintsResponsesRepository.save(complainResponse);

            //change the last change date for the complaint
            updateLastChangeDateOfComplain(complainResponse.getComplainId());

            //add response object to result map
            res.put("complaintResponse",complainResponse);

            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }




    public Object getAllComplaintsMessages(int complainId){
        Map<Object,Object> res = new HashMap<>();
        try{

            res.put("complainMessages",complaintsMessagesRepository.findAllByComplainId(complainId));

            //if any thing goes well add status to success
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object getAllComplaintsResponses(int complainId){
        Map<Object,Object> res = new HashMap<>();
        try{

            res.put("complainResponses",complaintsResponsesRepository.findAllByComplainId(complainId));

            //if any thing goes well add status to success
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            //if any error happen add status to error and add error cause
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }






    public Object updateStatusToClose(int complainId){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update status of complain to close
            complaintsRepository.updateStatusToClose(complainId);

            //change the last change date for the complaint
            updateLastChangeDateOfComplain(complainId);

            //add if updated successfully add update status with true
            res.put("updateStatus",true);
            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("updateStatus",false);
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object updateLastChangeDateOfComplain(int complainId){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update complain last change date by complain id
            complaintsRepository.updateComplainLastUpdateDateByComplainId(complainId);

            //add if updated successfully add update status with true
            res.put("updateStatus",true);
            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("updateStatus",false);
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }


    public Object getAllComplaintsByCreatedDateByPagination(LimitAndOffsetRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //getting all complaints order by created date then add them to result map
            Object users = complaintsRepository.getAllComplainsByCreatedAsc(request.getLimit(), request.getOffset());
            res.put("complaints",users);

            //getting count of all users
            long count = complaintsRepository.count();

            //add the result to result map
            res.put("count",count);

            //if success add the status of response to success
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            //if any exception happen add status is error and add error reason to know
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;

    }

    public Object getAllUsersFilteredByUserEmailByPagination(FilterComplaintsByemailLikeRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //getting all complaints filter by email like then add them to result map
            Object users = complaintsRepository.getAllComplainsByEmailLikeCount(
                    request.getLimit(),
                    request.getOffset(),
                    "%"+request.getEmail()+"%"
            );

            res.put("complaints",users);

            //getting count of all complaints returned when filter by email like
            long count = complaintsRepository.getAllComplainsByEmailLikeCount(
                    Integer.MAX_VALUE,
                    0,
                    "%"+request.getEmail()+"%"
            );

            //add the result to result map
            res.put("count",count);

            //if success add the status of response to success
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            //if any exception happen add status is error and add error reason to know
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;

    }


}