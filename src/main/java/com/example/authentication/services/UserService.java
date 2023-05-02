package com.example.authentication.services;

import com.example.authentication.controllers.AdminUsersController;
import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.repositories.usersRepo.UsersRepository;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.userRequests.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
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
public class UserService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired AdminUserService adminUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (AdminUsersController.ADMIN_USER.equals("admin")){
            return adminUserService.loadUserByUsername(username);
        }else{
            return findByUsername(username);
        }

    }

    private AppUser findByUsername(String username){
        AppUser appUser = new AppUser();
        try{
            Users  users =  usersRepository.findByUsernameAndAccountStatus(username,"active");

            appUser.setUsername(users.getUsername());
            appUser.setPassword(users.getPassword());
            appUser.setPhone(users.getPhone());
            appUser.setId(users.getId());
            return appUser;

        }catch (Exception e){
            System.err.println("error in getting user by user name ");
            e.printStackTrace();
            return null;
        }
    }

    public Users findUserByPhone(String phone){
        return usersRepository.findByPhoneAndAccountStatus(phone,"active");
    }



    public Object findByPhone(String phone){
        Map<Object,Object> res = new HashMap<>();
        try {

            //getting user data and transfer it for light user and return it to response map
            Users u = usersRepository.findByPhone(phone);
            res.put("user",u.lightUser(u));

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

    public Object getUserPhoto(int userId){
        Map<Object,Object> res = new HashMap<>();
        try {

            //getting user data and transfer it for light user and return it to response map
            String userPhoto = usersRepository.userPhoto(userId);
            res.put("photo",userPhoto);

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
            Users u = usersRepository.findById(userId);
            res.put("user",u.lightUser(u));

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

    public String createRandomPW(){

        try{
            String ALPHA_NUMERIC_STRING = "0123456789";

            StringBuilder builder = new StringBuilder();
            int count = 4;
            while (count-- != 0) {
                int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
            return builder.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Object sendOTP(String phone){
        Map<Object,Object> res = new HashMap<>();
        try{
            String ACCOUNT_SID = "ACd8d26f4dd0be94a2e6daab3906b8f27c";
            String AUTH_TOKEN = "3fad1ed6d98c187c094632026b40ac6a";
            //create random number
            String randomOTP= createRandomPW();

            String msg = "your verification code is "+randomOTP+" .";
            //send an otp by twillio
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(phone),
                            "MG512d5bf5fb2c9cf972ffad0611612f64",
                            msg)
                    .create();

            System.out.println(message.getSid());

            //add if sent successfully add update status with true
            res.put("otp",randomOTP);
            //add success status to response map
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            //if error occur because  any reason add error status and error reason
            res.put("otp","false");
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

    public Map<Object, Object> register(UserRegisterRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //encrypt password to md5
            String password = request.getPassword();
            password = encryption(password);

            //create user object to add data to it
            Users user = new Users(request.getPhone(), password);

            //save user data in database
            user = usersRepository.save(user);

            //add user object to result map
            res.put("user",user.lightUser(user));

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

    public Object updatePhone(UpdateUserPhoneRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update phone by new one and update username with the new phone
            usersRepository.updatePhone(request.getPhone(), request.getUserId());

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

    public Object updateAdditionalPhone(UpdateUserAdditionalPhoneRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update additional phone by new one with the new phone
            usersRepository.updateAdditionalPhone(request.getAdditionalPhone(), request.getUserId());

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

    public Object updatePhoto(UpdateUserPhotoRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update photo by the new photo
            usersRepository.updatePhoto(request.getPhoto(), request.getUserId());

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

    public Object updateName(UpdateUserNameRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //update name by the new name
            usersRepository.updateName(request.getName(), request.getUserId());

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
            usersRepository.updateEmail(request.getEmail(), request.getUserId());

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
            usersRepository.updateUserPasswordById(request.getUserId(), password);

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

    public Object updatePasswordByPhone(UpdateUserPasswordRequest request){
        Map<Object,Object> res = new HashMap<>();
        try{

            //encrypt new password
            String password = request.getPassword();
            password= encryption(password);

            //update encrypted password by the new password  by user phone
            usersRepository.updateUserPasswordByUsername(request.getPhone(), password);

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
            usersRepository.deleteAccount(userId);

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
            usersRepository.stopAccount(userId);

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
            usersRepository.activeAccount(userId);

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






    public Object getLatestJoiningUsersCount(){
        Map<Object,Object> res = new HashMap<>();
        try{
            //getting count of users that added today

            res.put("count",usersRepository.latestJoinedUsersCount(Integer.MAX_VALUE,0,
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

    public Object filterUsersByDateBetweenWithPagination(FilterUsersByDateRequest request){
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
            Object users = usersRepository.filterUsersByDate(request.getLimit(), request.getOffset(),
                   sDate,eDate , status);
            res.put("users",users);

            //getting count of all users that between this range of date
            long count = usersRepository.filterUsersByDateCount(Integer.MAX_VALUE,0,
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

    public Object getAllUsersCount(){
        Map<Object,Object> res = new HashMap<>();
        try{
            //getting count of all users
            res.put("count",usersRepository.count());
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

    public Object getAllUsersByPagination(LimitAndOffsetRequest request){
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
            Object users = usersRepository.getAllUsersAsc(request.getLimit(), request.getOffset() , status);
            res.put("users",users);

            //getting count of all users
            long count = usersRepository.getAllUsersAscCount(Integer.MAX_VALUE, 0 , status);

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

    public Object getAllUsersFilteredByNameLikeByPagination(FilterUsersByNameLikeRequest request){
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
            Object users = usersRepository.filterUsersByNameLikeAndStatusIn(
                    request.getLimit(),
                    request.getOffset(),
                    "%"+request.getName()+"%",
                    status
            );

            res.put("users",users);

            //getting count of all users returned when filter by name like
            long count = usersRepository.filterUsersByNameLikeCount(
                    Integer.MAX_VALUE,
                    0,
                    "%"+request.getName()+"%",
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