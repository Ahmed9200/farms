package com.example.authentication.services;

import com.example.authentication.repositories.CategoriesRepository;
import com.example.authentication.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsService {

    @Autowired
    ItemsRepository itemsRepository;


}