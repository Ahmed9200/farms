package com.example.farms.controllers;

import com.example.farms.DTO.Add.AddContactUsDTO;
import com.example.farms.DTO.Update.UpdateContactUsRequest;
import com.example.farms.services.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactUsController {

    @Autowired
    private ContactUsService service;



    @PostMapping(value = "/saveContactUs", produces = {"application/json"})
    @ResponseBody
    public Object saveContactUs(@Valid @RequestBody AddContactUsDTO request) {
        return service.saveContactUs(request);
    }

    @PostMapping(value = "/updateContactUs", produces = {"application/json"})
    @ResponseBody
    public Object updateContactUs(@Valid@RequestBody UpdateContactUsRequest request) {
        return service.updateContactUs(request);
    }

    @PostMapping(value = "/deleteContact/{contactId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteContact(@PathVariable("contactId") int id) {
        return service.deleteContactUs(id);
    }

    @GetMapping(value = "/viewAllContacts", produces = {"application/json"})
    @ResponseBody
    public Object viewAllContacts() {
        return service.viewAllContactUs();
    }

    @GetMapping(value = "/getContactById/{id}", produces = {"application/json"})
    @ResponseBody
    public Object getContactById(@PathVariable("id") int id) {
        return service.getContactById(id);
    }

    @GetMapping(value = "/getContactByUsername/{name}", produces = {"application/json"})
    @ResponseBody
    public Object getContactByUsername(@PathVariable("name") String name) {
        return service.getContactByName(name);
    }

    @GetMapping(value = "/getContactByPhone/{phone}", produces = {"application/json"})
    @ResponseBody
    public Object getContactByPhone(@PathVariable("phone") String phone) {
        return service.getContactByPhone(phone);
    }
}
