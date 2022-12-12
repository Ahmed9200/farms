package com.example.authentication.controllers;

import com.example.authentication.models.AdminUsers;
import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.adminRequests.AdminUserRegisterRequest;
import com.example.authentication.requests.adminRequests.AdminUserSignInDto;
import com.example.authentication.requests.adminRequests.FilterAdminUsersByEmailLikeRequest;
import com.example.authentication.requests.adminRequests.UpdateAdminUserPasswordRequest;
import com.example.authentication.requests.userRequests.*;
import com.example.authentication.responses.JwtResponse;
import com.example.authentication.services.AdminUserService;
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
@RequestMapping("/admins")
public class AdminUsersController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdminUserService userService;

    @Autowired
    UserService uService ;

    @Autowired
    private AuthenticationManager authenticationManager;

    public static String ADMIN_USER="admin";

    @PostMapping(value = "/register", produces = {"application/json"})
    @ResponseBody
    public Object register(@RequestBody AdminUserRegisterRequest request) {
        Map<Object,Object> res = new HashMap<>();
        try {

            res = userService.register(request);

        } catch (Exception e) {
            e.printStackTrace();
            //if any error happen add status and error reason.

            res.put("status","error");
            res.put("error","authentication faild !! email or password is wrong");
        }

        return res;
    }


    @PostMapping(value = "/adminLogin", produces = {"application/json"})
    @ResponseBody
    public Object adminLogin(@RequestBody AdminUserSignInDto signInRequest) {
        Map<Object,Object> res = new HashMap<>();
        ADMIN_USER="admin";
        try {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                        (signInRequest.getPassword()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) uService.loadUserByUsername(signInRequest.getEmail());
        String token = tokenService.generateUserToken(userDetails);
        res.put("token",new JwtResponse(token,tokenService.getClaims(token).getExpiration()));
        res.put("user",new AdminUsers().lightAdminUser(userService.findUserByEmail(userDetails.getPhone())));

        } catch (Exception e) {
            e.printStackTrace();
            res.put("error","authentication faild !! email or password is wrong");

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





    @PostMapping(value = "/updateEmailStatus")
    public Object updateEmail(@RequestHeader("Authorization") String token) {

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
        return userService.updateEmailStatus(tokenRes.get("userId").toString());
    }


    @PostMapping(value = "/updateEmail")
    public Object updateEmailStatus(@RequestBody UpdateUserEmailRequest request,
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


    @PostMapping(value = "/updatePasswordByEmail")
    public Object updatePasswordByPhone(@RequestBody UpdateAdminUserPasswordRequest request,
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
        return userService.updatePasswordByEmail(request);
    }






    @GetMapping(value = "/findUserById/{userId}")
    public Object findUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }

    @GetMapping(value = "/findUserByEmail/{email}")
    public Object findUserByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }





    @GetMapping(value = "/countOfAllAdminUsers")
    public Object countOfAllUsers() {
        return userService.getAllAdminUsersCount();
    }

    @GetMapping(value = "/countOfAllNewJoinedAdminUsers")
    public Object countOfAllNewJoinedAdminUsers() {
        return userService.getLatestJoiningAdminUsersCount();
    }





    @PostMapping(value = "/filterAdminUsersByEmailLike")
    public Object filterAdminUsersByEmailLike(@RequestBody FilterAdminUsersByEmailLikeRequest request) {
        return userService.getAllAdminUsersFilteredByEmailLikeByPagination(request);
    }

    @PostMapping(value = "/filterAdminUsersByDateBetween")
    public Object filterAdminUsersByDateBetween(@RequestBody FilterUsersByDateRequest request) {
        return userService.filterAdminUsersByDateBetweenWithPagination(request);
    }





    @PostMapping(value = "/allAdminUsers")
    public Object allUsers(@RequestBody LimitAndOffsetRequest request) {

        return userService.getAllAdminUsersByPagination(request);
    }



}
