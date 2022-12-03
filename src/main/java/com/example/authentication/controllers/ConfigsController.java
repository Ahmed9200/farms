package com.example.authentication.controllers;

import com.example.authentication.services.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configs")
public class ConfigsController {

    @Autowired
    ConfigService configService;

    @GetMapping(value = "/reloadConfigs")
    public Object reloadConfigs() {

        return configService.reloadConfigs();
    }

}
