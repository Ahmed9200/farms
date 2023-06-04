package com.example.farms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] PUBLIC_ENDPOINTS = {
            "/users/login",
            "/users/forgetPassword/{username}",
            "/banner/viewAllBanners",
            "/banner/getBannerById/{bannerId}",
            "/category/viewAllCategories",
            "/contact/saveContactUs",
            "/exp_years/viewAllExpYear",
            "/exp_years/getExpYearById/{expYearId}",
            "/exp_years/getExpYearByYear/{year}",
            "/invoice/saveInvoice",
            "/invoice/cartItems/{invoiceId}",
            "/items/getItemById/{itemId}",
            "/items/getItemByNameLike/{name}",
            "/items/getItemByCategory/{categoryId}",
            "/items/allSaleItems",
            "/items/allNewItems",
            "/items/allItems",
            "/partners/viewAllPartner",
            "/invoice/getVat",
            "/invoice/getDelivery"
    };

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new MessageDigestPasswordEncoder("MD5");
    }


    @Autowired
    AuthFilter authFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
