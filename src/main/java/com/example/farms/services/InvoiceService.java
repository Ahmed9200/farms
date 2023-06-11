package com.example.farms.services;

import com.example.farms.DTO.Add.AddCartDto;
import com.example.farms.DTO.Add.AddInvoiceDto;
import com.example.farms.models.entities.CartItems;
import com.example.farms.models.entities.Categories;
import com.example.farms.models.entities.Invoices;
import com.example.farms.models.enums.InvoiceStatus;
import com.example.farms.repositories.CartItemsRepository;
import com.example.farms.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Service
public class InvoiceService {

    @Autowired
    InvoicesRepository invoicesRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;


    public Object saveInvoice(AddInvoiceDto request) {
        HashMap<Object,Object> res = new HashMap<>();
        Invoices invoice = new Invoices(request);
        invoice = invoicesRepository.save(invoice);
        saveCart(request.getItems(), invoice.getId());
        res.put("status","success");
        res.put("invoiceId",invoice.getId());
        return res;
    }
    public void saveCart(List<AddCartDto> request,int invoiceId){
        for (AddCartDto r : request){
            CartItems cartItems = new CartItems(r,invoiceId);
            cartItems=cartItemsRepository.save(cartItems);
//            cartItemsRepository.updateQuantity(r.getQuantity(),r.getItemId());
        }
    }


    public Object deleteInvoice(int id) {

        Map<Object,Object> res = new HashMap<>();
        try{
            cartItemsRepository.deleteAllByInvoiceId(id);
            invoicesRepository.deleteById(id);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object makeInvoiceStatusWaiting(int id) {

        Map<Object,Object> res = new HashMap<>();
        try{
            invoicesRepository.updateStatus(0,id);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }


    public Object makeInvoiceStatusInProgress(int id) {

        Map<Object,Object> res = new HashMap<>();
        try{
            invoicesRepository.updateStatus(1,id);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object makeInvoiceStatusDone(int id) {

        Map<Object,Object> res = new HashMap<>();
        try{
            invoicesRepository.updateStatus(2,id);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }
    public Object makeInvoiceStatusCanceled(int id) {

        Map<Object,Object> res = new HashMap<>();
        try{
            invoicesRepository.updateStatus(3,id);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object allInvoices(int page, int size) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size,Sort.by("date"));
        Page<Invoices> invoices= invoicesRepository.findAll(pageable);
        res.put("invoices",invoices.get());
        res.put("total",invoices.getTotalElements());
        return res;
    }

    public Object allInvoicesByUsername(int page, int size,String username) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by("date"));
        Page<Invoices> invoices=invoicesRepository.findAllByUsernameLike(username,pageable);
        res.put("invoices",invoices.get());
        res.put("total",invoices.getTotalElements());
        res.put("totalPrice",invoicesRepository.getSumOfInvoicesByUser(username).get("total"));
        return res;
    }

    public Object allInvoicesByDate(int page, int size, Date start, Date end) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by("date"));
        Calendar c = Calendar.getInstance();
        c.setTime(end);
        c.add(Calendar.DATE, 1);
        end = c.getTime();
        Page<Invoices> invoices=invoicesRepository.findAllByDateBetween(start,end,pageable);
        res.put("invoices",invoices.get());
        res.put("total",invoices.getTotalElements());
        res.put("totalPrice",invoicesRepository.getSumOfInvoicesByDate(start, end).get("total"));
        return res;
    }

    public Object allInvoiceCart(int id) {
        return cartItemsRepository.getInvoiceItems(id);
    }

    public Object totalInvoicesPrice() {
        return invoicesRepository.getSumOfInvoices();
    }

    public Object invoiceById(int id) {
        Map<Object,Object> res = new HashMap<>();

        Optional<Invoices> invoices = invoicesRepository.findById(id);
        if (invoices.isPresent()){
            res.put("status","success");//invoices not found
            res.put("invoice",invoices.get());
        }else {
            res.put("status", "error");
            res.put("error", "INVOICE-REP-001");//category not found
        }
        return res;

    }
}