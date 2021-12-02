package com.example.authentication.services;

import com.example.authentication.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements UserDetailsService {

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${constantsData.userByUsername}")
    String userByUsernameApi;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = findByUsername(username);
        return user;
    }

    private AppUser findByUsername(String username){
        try{
            return restTemplate.getForObject(userByUsernameApi+username,AppUser.class);
        }catch (Exception e){
            return null;
        }
    }

}