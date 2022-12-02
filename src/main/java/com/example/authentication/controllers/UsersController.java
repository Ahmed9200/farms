package com.example.authentication.controllers;

import com.example.authentication.models.AppUser;
import com.example.authentication.responses.userResponses.JwtResponse;
import com.example.authentication.requests.userRequests.UserSignInDto;
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
public class UsersController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping(value = "/users/login", produces = {"application/json"})
    @ResponseBody
    public Object login(@RequestBody UserSignInDto signInRequest) {
        Map<Object,Object> res = new HashMap<>();
        try {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getPhone(),
                        (signInRequest.getPassword()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) userService.loadUserByUsername(signInRequest.getPhone());
        String token = tokenService.generateUserToken(userDetails);
        res.put("token",new JwtResponse(token,tokenService.getClaims(token).getExpiration()));
        res.put("user",userService.findUserByPhone(userDetails.getPhone()));

        } catch (Exception e) {
            e.printStackTrace();
            res.put("error","authentication faild !! username or password is wrong");

        }
        return res;
    }



    @GetMapping(value = "/test", produces = {"application/json"})
    @ResponseBody
    public Object test() {
        try{
            ArrayList<String> strings = new ArrayList<>();
            strings.add("1");
            strings.add("2");
            strings.add("3");
            strings.add("4");
            strings.add("5");
            strings.add("6");
            strings.add("7");
            strings.add("8");
            return strings;
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
