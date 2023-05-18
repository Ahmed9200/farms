package com.example.authentication.services;

import com.example.authentication.repositories.ExperiencesYearsRepository;
import com.example.authentication.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceYearService {

    @Autowired
    ExperiencesYearsRepository repository;


}