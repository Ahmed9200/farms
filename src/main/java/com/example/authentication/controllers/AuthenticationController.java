package com.example.authentication.controllers;

import com.example.authentication.models.AppUser;
import com.example.authentication.models.JwtResponse;
import com.example.authentication.models.SignInDto;
import com.example.authentication.services.TokenService;
import com.example.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value = "/getToken", produces = {"application/json"})
    @ResponseBody
    public Object getToken(@RequestBody SignInDto signInRequest) {
        try {
            signInRequest.setUsername(signInRequest.getUsername().toLowerCase());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        (signInRequest.getPassword()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) userService.loadUserByUsername(signInRequest.getUsername());

        String token = tokenService.generateUserToken(userDetails);
            return new JwtResponse(token,tokenService.getClaims(token).getExpiration());
        } catch (Exception e) {
            e.printStackTrace();
            Map<Object,Object> res = new HashMap<>();
            res.put("error","authentication faild !! username or password is wrong");
            return res;
        }
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


    @GetMapping(value = "/isTokenValid/{token}", produces = {"application/json"})
    @ResponseBody
    public Object isTokenValid(@PathVariable("token") String token) {
        Map<Object,Object> res = new HashMap<>();
        res.put("isValid",tokenService.isTokenValid(token));
        res.put("token",token);
        try{
            res.put("expirationDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(tokenService.getClaims(token).getExpiration()));
        }catch (Exception e){
        }
        return res;
    }
}
