package com.example.authentication.controllers;

import com.example.authentication.services.InvoiceService;
import com.example.authentication.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoicesController {

    @Autowired
    private InvoiceService invoiceService;


}
