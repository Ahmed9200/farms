package com.example.authentication.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AppUser implements UserDetails{
    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private int id;
    private String username;
    private String role;
    private String displayName;
    private String password;

    public AppUser() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public int getId(){return id;};

    public String getRole(){return role;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}