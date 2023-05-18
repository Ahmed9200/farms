package com.example.authentication.services;

import com.example.authentication.repositories.CartItemsRepository;
import com.example.authentication.repositories.InvoicesRepository;
import com.example.authentication.repositories.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    InvoicesRepository invoicesRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;


}