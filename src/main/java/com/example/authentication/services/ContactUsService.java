package com.example.authentication.services;

import com.example.authentication.repositories.ContactUsRepository;
import com.example.authentication.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService {

    @Autowired
    ContactUsRepository contactUsRepository;


}