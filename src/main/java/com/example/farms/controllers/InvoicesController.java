package com.example.farms.controllers;

import com.example.farms.DTO.Add.AddInvoiceDto;
import com.example.farms.DTO.Add.AddItemDTO;
import com.example.farms.models.enums.Constants;
import com.example.farms.services.ConfigService;
import com.example.farms.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/invoice")
public class InvoicesController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    ConfigService configService;


    @PostMapping(value = "/saveInvoice", produces = {"application/json"})
    @ResponseBody
    public Object saveItem(@Valid @RequestBody AddInvoiceDto request) {
        return invoiceService.saveInvoice(request);
    }

    @PostMapping(value = "/deleteInvoice/{id}", produces = {"application/json"})
    @ResponseBody
    public Object deleteInvoice(@PathVariable("id") int id) {
        return invoiceService.deleteInvoice(id);
    }


    @PostMapping(value = "/waitingStatus/{id}", produces = {"application/json"})
    @ResponseBody
    public Object waitingStatus(@PathVariable("id") int id) {
        return invoiceService.makeInvoiceStatusWaiting(id);
    }


    @PostMapping(value = "/inProgressStatus/{id}", produces = {"application/json"})
    @ResponseBody
    public Object inProgressStatus(@PathVariable("id") int id) {
        return invoiceService.makeInvoiceStatusInProgress(id);
    }


    @PostMapping(value = "/doneStatus/{id}", produces = {"application/json"})
    @ResponseBody
    public Object doneStatus(@PathVariable("id") int id) {
        return invoiceService.makeInvoiceStatusDone(id);
    }

    @PostMapping(value = "/canceledStatus/{id}", produces = {"application/json"})
    @ResponseBody
    public Object canceledStatus(@PathVariable("id") int id) {
        return invoiceService.makeInvoiceStatusCanceled(id);
    }


    @GetMapping(params = { "page", "size"},
            value = "/allInvoices",
            produces = {"application/json"})
    @ResponseBody
    public Object allInvoices(
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size) {
        return invoiceService.allInvoices(page,size);
    }


    @GetMapping(params = { "page", "size" ,"username"},
            value = "/allInvoicesByUser",
            produces = {"application/json"})
    @ResponseBody
    public Object allInvoicesByUser(
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size) {
        return invoiceService.allInvoicesByUsername(page,size,username);
    }

    @GetMapping(params = { "page", "size" ,"start","end"},
            value = "/allInvoicesByDate",
            produces = {"application/json"})
    @ResponseBody
    public Object allInvoicesByDate(
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam(value = "end")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size) {
        return invoiceService.allInvoicesByDate(page,size,start,end);
    }

    @GetMapping(value = "/cartItems/{invoiceId}",
            produces = {"application/json"})
    @ResponseBody
    public Object cartItems(@PathVariable("invoiceId") int id) {
        return invoiceService.allInvoiceCart(id);
    }

    @GetMapping(value = "/totalInvoicesPrice",
            produces = {"application/json"})
    @ResponseBody
    public Object totalInvoicesPrice() {
        return invoiceService.totalInvoicesPrice();
    }

    @GetMapping(value = "/invoice/{id}",
            produces = {"application/json"})
    @ResponseBody
    public Object invoice(@PathVariable("id") int id) {
        return invoiceService.invoiceById(id);
    }



    @GetMapping(value = "/getVat",
            produces = {"application/json"})
    @ResponseBody
    public Object getVat() {
        return Constants.VAT;
    }


    @GetMapping(value = "/getDelivery",
            produces = {"application/json"})
    @ResponseBody
    public Object getDelivery() {
        return Constants.DELIVERY;
    }


    @PostMapping(value = "/updateVat/{value}", produces = {"application/json"})
    @ResponseBody
    public Object updateVat(@PathVariable("value") int value) {
        return configService.saveVat(value);
    }


    @PostMapping(value = "/updateDelivery/{value}", produces = {"application/json"})
    @ResponseBody
    public Object updateDelivery(@PathVariable("value") int value) {
        return configService.saveDelivery(value);
    }


}
