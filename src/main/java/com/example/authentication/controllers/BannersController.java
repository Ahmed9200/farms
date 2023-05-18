package com.example.authentication.controllers;

import com.example.authentication.services.BannersService;
import com.example.authentication.services.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannersController {

    @Autowired
    private BannersService bannersService;


}
