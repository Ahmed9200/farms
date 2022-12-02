package com.example.authentication.services;

import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
            Users users =  usersRepository.findByUsername(username);
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
        return usersRepository.findByPhone(phone);
    }

}