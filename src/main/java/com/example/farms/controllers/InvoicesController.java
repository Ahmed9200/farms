package com.example.farms.controllers;

import com.example.farms.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoicesController {

    @Autowired
    private InvoiceService invoiceService;


}
