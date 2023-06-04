package com.example.farms.controllers;

import com.example.farms.DTO.Add.AddPartnerDTO;
import com.example.farms.DTO.Update.UpdatePartnerRequest;
import com.example.farms.services.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/partners")
public class PartnersController {

    @Autowired
    private PartnersService partnersService;


    @PostMapping(value = "/savePartner", produces = {"application/json"})
    @ResponseBody
    public Object savePartner(@Valid @RequestBody AddPartnerDTO request) {
        return partnersService.savePartner(request);
    }

    @PostMapping(value = "/updatePartner", produces = {"application/json"})
    @ResponseBody
    public Object updatePartner(@Valid@RequestBody UpdatePartnerRequest request) {
        return partnersService.updatePartner(request);
    }

    @PostMapping(value = "/deletePartner/{partnerId}", produces = {"application/json"})
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
