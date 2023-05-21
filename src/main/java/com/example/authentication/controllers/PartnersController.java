package com.example.authentication.controllers;

import com.example.authentication.requests.Add.AddPartnerRequest;
import com.example.authentication.requests.Update.UpdatePartnerRequest;
import com.example.authentication.services.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partners")
public class PartnersController {

    @Autowired
    private PartnersService partnersService;


    @PostMapping(value = "/savePartner", produces = {"application/json"})
    @ResponseBody
    public Object savePartner(@RequestBody AddPartnerRequest request) {
        return partnersService.savePartner(request);
    }

    @PutMapping(value = "/updatePartner", produces = {"application/json"})
    @ResponseBody
    public Object updatePartner(@RequestBody UpdatePartnerRequest request) {
        return partnersService.updatePartner(request);
    }

    @DeleteMapping(value = "/deletePartner/{partnerId}", produces = {"application/json"})
    @ResponseBody
    public Object deletePartner(@PathVariable("partnerId") int partnerId) {
        return partnersService.deletePartner(partnerId);
    }

    @GetMapping(value = "/viewAllPartner", produces = {"application/json"})
    @ResponseBody
    public Object viewAllPartner() {
        return partnersService.viewAllPartners();
    }

    @GetMapping(value = "/getPartnerById/{partnerId}", produces = {"application/json"})
    @ResponseBody
    public Object getPartnerById(@PathVariable("partnerId") int partnerId) {
        return partnersService.getPartnerById(partnerId);
    }

    @GetMapping(value = "/getPartnerByNameLike/{name}", produces = {"application/json"})
    @ResponseBody
    public Object getExpYearById(@PathVariable("name") String name) {
        return partnersService.getPartnerByNameLike(name);
    }


}
