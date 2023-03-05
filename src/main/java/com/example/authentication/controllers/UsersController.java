package com.example.authentication.controllers;

import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.userRequests.*;
import com.example.authentication.responses.JwtResponse;
import com.example.authentication.services.TokenService;
import com.example.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;




    @PostMapping(value = "/register", produces = {"application/json"})
    @ResponseBody
    public Object register(@RequestBody UserRegisterRequest request) {
        Map<Object,Object> res = new HashMap<>();
        try {

            res = userService.register(request);

        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
            res.put("error","authentication faild !! phone or password is wrong");
        }

        return res;
    }


    @PostMapping(value = "/login", produces = {"application/json"})
    @ResponseBody
    public Object login(@RequestBody UserSignInDto signInRequest) {
        Map<Object,Object> res = new HashMap<>();
        try {
            AdminUsersController.ADMIN_USER="user";
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getPhone(),
                        (signInRequest.getPassword()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) userService.loadUserByUsername(signInRequest.getPhone());
        String token = tokenService.generateUserToken(userDetails);
        res.put("token",new JwtResponse(token,tokenService.getClaims(token).getExpiration()));
        res.put("user",new Users().lightUser(userService.findUserByPhone(userDetails.getPhone())));

        } catch (Exception e) {
            e.printStackTrace();
            res.put("error","authentication faild !! username or password is wrong");

        }
        return res;
    }








    @PostMapping(value = "/deleteAccount")
    public Object deleteAcc(@RequestHeader("Authorization") String token) {

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
        return userService.deleteAccount(Integer.parseInt(tokenRes.get("userId").toString()));
    }

    @PostMapping(value = "/stopAccount")
    public Object stopAcc(@RequestHeader("Authorization") String token) {

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
        return userService.stopAccount(Integer.parseInt(tokenRes.get("userId").toString()));
    }

    @GetMapping(value = "/activeAccount/{userId}")
    public Object activeAcc(@PathVariable("userId") int userId) {
        return userService.activeAccount(userId);
    }






    @PostMapping(value = "/updateName")
    public Object updateName(@RequestBody UpdateUserNameRequest request,
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
        return userService.updateName(request);
    }


    @PostMapping(value = "/updatePhone")
    public Object updatePhone(@RequestBody UpdateUserPhoneRequest request,
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
        return userService.updatePhone(request);
    }


    @PostMapping(value = "/updateAdditionalPhone")
    public Object updateAdditionalPhone(@RequestBody UpdateUserAdditionalPhoneRequest request,
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
        return userService.updateAdditionalPhone(request);
    }


    @PostMapping(value = "/updatePhoto")
    public Object updatePhoto(@RequestBody UpdateUserPhotoRequest request,
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
        return userService.updatePhoto(request);
    }


    @PostMapping(value = "/updateEmail")
    public Object updatePhone(@RequestBody UpdateUserEmailRequest request,
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
        return userService.updateEmail(request);
    }


    @PostMapping(value = "/updatePasswordById")
    public Object updatePasswordById(@RequestBody UpdateUserPasswordRequest request,
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
        return userService.updatePasswordById(request);
    }


    @PostMapping(value = "/updatePasswordByPhone")
    public Object updatePasswordByPhone(@RequestBody UpdateUserPasswordRequest request,
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
        return userService.updatePasswordByPhone(request);
    }


    @PostMapping(value = "/changePW")
    public Object changePW(@RequestBody UpdateUserPasswordRequest request) {
        return userService.updatePasswordByPhone(request);
    }





    @GetMapping(value = "/findUserById/{userId}")
    public Object findUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }

    @GetMapping(value = "/findUserByPhone/{phone}")
    public Object findUserById(@PathVariable("phone") String phone) {
        return userService.findByPhone(phone);
    }


    @GetMapping(value = "/userPhoto/{userId}")
    public Object userPhoto(@PathVariable("userId") int userId) {
        return userService.getUserPhoto(userId);
    }






    @GetMapping(value = "/countOfAllUsers")
    public Object countOfAllUsers() {

        return userService.getAllUsersCount();
    }

    @GetMapping(value = "/countOfAllNewJoinedUsers")
    public Object countOfAllNewJoinedUsers() {

        return userService.getLatestJoiningUsersCount();
    }

    @PostMapping(value = "/filterUsersByNameLike")
    public Object filterUsersByNameLike(@RequestBody FilterUsersByNameLikeRequest request) {

        return userService.getAllUsersFilteredByNameLikeByPagination(request);
    }

    @PostMapping(value = "/filterUsersByDateBetween")
    public Object filterUsersByDateBetween(@RequestBody FilterUsersByDateRequest request) {

        return userService.filterUsersByDateBetweenWithPagination(request);
    }

    @PostMapping(value = "/allUsers")
    public Object allUsers(@RequestBody LimitAndOffsetRequest request) {

        return userService.getAllUsersByPagination(request);
    }



}
