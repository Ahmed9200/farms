package com.example.farms.controllers;

import com.example.farms.models.entities.AppUser;
import com.example.farms.models.entities.Users;
import com.example.farms.DTO.Add.UserRegisterDTO;
import com.example.farms.DTO.Get.UserSignInDto;
import com.example.farms.DTO.Update.*;
import com.example.farms.responses.JwtResponse;
import com.example.farms.services.TokenService;
import com.example.farms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Object register(@RequestBody UserRegisterDTO request) {
            return userService.register(request);
    }

    @PostMapping(value = "/login", produces = {"application/json"})
    @ResponseBody
    public Object login(@RequestBody UserSignInDto signInRequest) {
        Map<Object,Object> res = new HashMap<>();
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser userDetails = (AppUser) userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenService.generateUserToken(userDetails);
        res.put("token",new JwtResponse(token,tokenService.getClaims(token).getExpiration()));
        res.put("user",new Users().lightUser(userService.findUserByUsername(signInRequest.getUsername())));
        return res;
    }

    @PostMapping(value = "/deleteUser/{userId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteItem(@Valid @PathVariable("userId") int userId) {
        return userService.deleteUser(userId);
    }


    @PostMapping(value = "/updateName")
    public Object updateName(@RequestBody UpdateUserNameRequest request) {
        return userService.updateName(request);
    }

    @PostMapping(value = "/updatePhone")
    public Object updatePhone(@RequestBody UpdateUserPhoneRequest request) {
        return userService.updatePhone(request);
    }

    @PostMapping(value = "/updatePasswordById")
    public Object updatePasswordById(@RequestBody UpdateUserPasswordRequest request) {
        return userService.updatePasswordById(request);
    }

    @PostMapping(value = "/forgetPassword/{username}")
    public Object changePW(@PathVariable("username") String username) {
        return userService.updatePassword(username);
    }

    @GetMapping(value = "/findUserById/{userId}")
    public Object findUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }

    @GetMapping(value = "/user")
    public Object findUserById(@RequestHeader("Authorization") String token) {
        return tokenService.getUser(token);
    }

    @GetMapping(value = "/allUsers")
    public Object allUsers() {
        return userService.allUsers();
    }



}
