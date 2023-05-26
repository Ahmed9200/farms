package com.example.farms.services;

import com.example.farms.repositories.CartItemsRepository;
import com.example.farms.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    InvoicesRepository invoicesRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;


}