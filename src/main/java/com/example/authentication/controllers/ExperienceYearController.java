package com.example.authentication.controllers;

import com.example.authentication.services.ExperienceYearService;
import com.example.authentication.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exp_years")
public class ExperienceYearController {

    @Autowired
    private ExperienceYearService service;


}
