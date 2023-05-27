package com.example.farms.services;

import com.example.farms.models.AppUser;
import com.example.farms.models.Users;
import com.example.farms.repositories.UsersRepository;
import com.example.farms.DTO.Add.UserRegisterDTO;
import com.example.farms.DTO.Get.FilterUsersByDateRequest;
import com.example.farms.DTO.Get.FilterUsersByNameLikeRequest;
import com.example.farms.DTO.Get.LimitAndOffsetRequest;
import com.example.farms.DTO.Update.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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

    @Override
    public UserDetails loadUserByUsername(String username) {
            return findByUsername(username);
    }

    private AppUser findByUsername(String username){
        AppUser appUser = new AppUser();
        try{
            Users  users =  usersRepository.findByUsername(username);
            appUser.setUsername(users.getUsername());
            appUser.setPassword(users.getPassword());
            appUser.setPhone(users.getPhone());
            appUser.setRole(users.getRole());
            appUser.setId(users.getId());
            return appUser;
        }catch (Exception e){
            return null;
        }
    }

    public Users findUserByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public Object findUserById(int userId){
        Map<Object,Object> res = new HashMap<>();
        Users u = usersRepository.findById(userId);
        res.put("user",u.lightUser(u));
        res.put("status","success");
        return res;
    }

    private String encryption(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public Map<Object, Object> register(UserRegisterDTO request){

        Map<Object,Object> res = new HashMap<>();
        Users user = new Users(request.getName(),
                request.getUsername(),
                encryption(request.getPassword()),
                request.getPhone(), request.getRole());
        System.err.println(user);
        user = usersRepository.save(user);
        res.put("user",user.lightUser(user));
        res.put("status","success");
        return res;
    }

    public Object updatePhone(UpdateUserPhoneRequest request){
        Map<Object,Object> res = new HashMap<>();
        usersRepository.updatePhone(request.getPhone(), request.getUserId());
        res.put("status","success");
        return res;
    }

    public Object updatePhoto(UpdateUserPhotoRequest request){
        Map<Object,Object> res = new HashMap<>();
        usersRepository.updatePhoto(request.getPhoto(), request.getUserId());
        res.put("status","success");
        return res;
    }

    public Object updateName(UpdateUserNameRequest request){
        Map<Object,Object> res = new HashMap<>();
        usersRepository.updateName(request.getName(), request.getUserId());
        res.put("status","success");
        return res;
    }

    public Object updatePasswordById(UpdateUserPasswordRequest request){
        Map<Object,Object> res = new HashMap<>();
        String password = request.getPassword();
        password= encryption(password);
        usersRepository.updateUserPasswordById(request.getUserId(), password);
        res.put("status","success");
        return res;
    }

    public Object updatePassword(String username){
        Map<Object,Object> res = new HashMap<>();
        String password = "12345";
        password= encryption(password);
        usersRepository.updateUserPasswordByUsername(username, password);
        res.put("password","12345");
        res.put("status","success");
        return res;
    }

    public Object allUsers() {
        Map<Object,Object> res = new HashMap<>();
        res.put("users",usersRepository.findAll());
        res.put("total",usersRepository.count());
        return res;
    }

    public Object deleteUser(int userId) {
        Map<Object,Object> res = new HashMap<>();
        try{
            usersRepository.deleteById(userId);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }
}