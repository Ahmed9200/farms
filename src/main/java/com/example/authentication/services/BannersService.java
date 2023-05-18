package com.example.authentication.services;

import com.example.authentication.repositories.BannersRepository;
import com.example.authentication.repositories.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannersService {

    @Autowired
    BannersRepository repository;


}