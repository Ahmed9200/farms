package com.example.authentication.services;

import com.example.authentication.models.AdminUsers;
import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.repositories.usersRepo.AdminsUsersRepository;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.adminRequests.AdminUserRegisterRequest;
import com.example.authentication.requests.adminRequests.FilterAdminUsersByEmailLikeRequest;
import com.example.authentication.requests.adminRequests.UpdateAdminRoleRequest;
import com.example.authentication.requests.adminRequests.UpdateAdminUserPasswordRequest;
import com.example.authentication.requests.userRequests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminUserService {

    @Autowired
    AdminsUsersRepository adminsUsersRepository;

    public UserDetails loadUserByUsername(String username) {
        return findByUsername(username);
    }

    private AppUser findByUsername(String email){
        AppUser appUser = new AppUser();
        try{
            AdminUsers users =  adminsUsersRepository.findByEmailAndAccountStatus(email,"active");
            System.err.println(users);
            appUser.setUsername(users.getUsername());
            appUser.setPassword(users.getPassword());
            appUser.setPhone(users.getEmail());
            appUser.setEmail(users.getEmail());
            appUser.setId(users.getId());
            return appUser;

        }catch (Exception e){
            System.err.println("error in getting admin by email ");
            e.printStackTrace();
            return null;
        }
    }

    public AdminUsers findUserByEmail(String email){
        return adminsUsersRepository.findByEmailAndAccountStatus(email,"active");
    }



    public Object findByEmail(String email){
        Map<Object,Object> res = new HashMap<>();
        try {

            //getting user data and transfer it for light user and return it to response map
            AdminUsers u = adminsUsersRepository.findByEmail(email);
            res.put("user",u.lightAdminUser(u));

            // adding status success if every thing goes well
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();

            // adding status error with error cause if any error occur
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object findUserById(int userId){
        Map<Object,Object> res = new HashMap<>();
        try {

            //getting user data and transfer it for light user and return it to response map
            AdminUsers u = adminsUsersRepository.findById(userId);
            res.put("user",u.lightAdminUser(u));

            // adding status success if every thing goes well
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();

            // adding status error with error cause if any error occur
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }



    private String encryption(String passwordToHash) {
        //String passwordToHash = "password";
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    } // funcation


    public Map<Object, Object> register(AdminUserRegisterRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //encrypt password to md5
            String password = request.getPassword();
            password = encryption(password);

            //create user object to add data to it
            AdminUsers user = new AdminUsers(request.getEmail(), password);

            //save user data in database
            user = adminsUsersRepository.save(user);

            //add user object to result map
            res.put("user",user.lightAdminUser(user));

            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("status","error");
            res.put("error","Duplicate entry for phone");
        }
        return res;
    }


    public Object updateEmailStatus(String userId){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update email by the new email
            adminsUsersRepository.updateEmailStatus("active", userId);

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


    public Object updateEmail(UpdateUserEmailRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update email by the new email
            adminsUsersRepository.updateEmail(request.getEmail(), request.getUserId());

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

    public Object updateRole(UpdateAdminRoleRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update email by the new email
            adminsUsersRepository.updateRole(request.getRole(), request.getUserId());

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

    public Object updatePasswordById(UpdateUserPasswordRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //encrypt new password
            String password = request.getPassword();
            password= encryption(password);

            //update encrypted password by the new password  by user id
            adminsUsersRepository.updateUserPasswordById(request.getUserId(), password);

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

    public Object updatePasswordByEmail(UpdateAdminUserPasswordRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //encrypt new password
            String password = request.getPassword();
            password= encryption(password);

            //update encrypted password by the new password  by user phone
            adminsUsersRepository.updateUserPasswordByEmail(request.getEmail(), password);

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






    public Object deleteAccount(int userId){
        Map<Object,Object> res = new HashMap<>();
        try{

            //delete account by adding for his phone affix 001_ before the number
            adminsUsersRepository.deleteAccount(userId);

            //add if deleted successfully add delete status with true
            res.put("deleteStatus",true);
            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("deleteStatus",false);
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object stopAccount(int userId){
        Map<Object,Object> res = new HashMap<>();
        try{

            System.err.println( userId);
            //delete account by adding for his phone affix 002_ before the number
            adminsUsersRepository.stopAccount(userId);

            //add if stopped successfully add stop status with true
            res.put("stopStatus",true);
            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("stopStatus",false);
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object activeAccount(int userId){
        Map<Object,Object> res = new HashMap<>();
        try{

            //delete account by removing for his phone affix 002_ before the number
            adminsUsersRepository.activeAccount(userId);

            //add if active successfully add active status with true
            res.put("activeStatus",true);
            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("activeStatus",false);
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }








    public Object getLatestJoiningAdminUsersCount(){
        Map<Object,Object> res = new HashMap<>();
        try{
            //getting count of users that added today

            res.put("count", adminsUsersRepository.latestJoinedAdminUsersCount(Integer.MAX_VALUE,0,
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
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

    public Object filterAdminUsersByDateBetweenWithPagination(FilterUsersByDateRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            Object status = null;

            if (request.getStatus().equals("all")){
                ArrayList<String> list = new ArrayList<>();
                list.add("active");
                list.add("stop");
                list.add("delete");
                status = list;
            }else status = request.getStatus();

            Date sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getStartDate()+" 00:00:01");
            Date eDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getEndDate()+" 12:59:59");
            //getting all users in this range then add them to result map
            Object users = adminsUsersRepository.filterAdminUsersByDate(request.getLimit(), request.getOffset(),
                   sDate,eDate , status);
            res.put("users",users);

            //getting count of all users that between this range of date
            long count = adminsUsersRepository.filterAdminUsersByDateCount(Integer.MAX_VALUE,0,
                   sDate,eDate , status);

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

    public Object getAllAdminUsersCount(){
        Map<Object,Object> res = new HashMap<>();
        try{
            //getting count of all users
            res.put("count", adminsUsersRepository.count());
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

    public Object getAllAdminUsersByPagination(LimitAndOffsetRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{
            Object status = null;
            if (request.getStatus().equals("all")){
                ArrayList<String> list = new ArrayList<>();
                list.add("active");
                list.add("stop");
                list.add("delete");
                status = list;
            }else status = request.getStatus();

            //getting all users then add them to result map
            Object users = adminsUsersRepository.getAllAdminUsersAsc(request.getLimit(), request.getOffset() , status);
            res.put("users",users);

            //getting count of all users
            long count = adminsUsersRepository.getAllAdminUsersAscCount(Integer.MAX_VALUE, 0 , status);

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

    public Object getAllAdminUsersFilteredByEmailLikeByPagination(FilterAdminUsersByEmailLikeRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            Object status = null;
            if (request.getStatus().equals("all")){
                ArrayList<String> list = new ArrayList<>();
                list.add("active");
                list.add("stop");
                list.add("delete");
                status = list;
            }else status = request.getStatus();

            //getting all users filter by name like then add them to result map
            Object users = adminsUsersRepository.filterAdminUsersByEmailLikeAndStatusIn(
                    request.getLimit(),
                    request.getOffset(),
                    "%"+request.getEmail()+"%",
                    status
            );

            res.put("users",users);

            //getting count of all users returned when filter by name like
            long count = adminsUsersRepository.filterAdminUsersByEmailLikeCount(
                    Integer.MAX_VALUE,
                    0,
                    "%"+request.getEmail()+"%",
                    status
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