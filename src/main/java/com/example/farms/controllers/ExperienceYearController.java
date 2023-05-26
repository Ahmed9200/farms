package com.example.farms.controllers;

import com.example.farms.DTO.Add.AddExpYearDTO;
import com.example.farms.DTO.Update.UpdateExpYearRequest;
import com.example.farms.services.ExperienceYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exp_years")
public class ExperienceYearController {

    @Autowired
    private ExperienceYearService service;


    @PostMapping(value = "/saveExpYear", produces = {"application/json"})
    @ResponseBody
    public Object saveExpYear(@Valid @RequestBody AddExpYearDTO request) {
        return service.saveExpYear(request);
    }

    @PutMapping(value = "/updateExpYear", produces = {"application/json"})
    @ResponseBody
    public Object updateExpYear(@Valid@RequestBody UpdateExpYearRequest request) {
        return service.updateExpYear(request);
    }

    @DeleteMapping(value = "/deleteExpYear/{expYearId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteExpYear(@PathVariable("expYearId") int expYearId) {
        return service.deleteExpYear(expYearId);
    }

    @GetMapping(value = "/viewAllExpYear", produces = {"application/json"})
    @ResponseBody
    public Object viewAllExpYear() {
        return service.viewAllExpYear();
    }

    @GetMapping(value = "/getExpYearById/{expYearId}", produces = {"application/json"})
    @ResponseBody
    public Object getExpYearById(@PathVariable("expYearId") int expYearId) {
        return service.getExpYearById(expYearId);
    }

    @GetMapping(value = "/getExpYearByYear/{year}", produces = {"application/json"})
    @ResponseBody
    public Object getExpYearById(@PathVariable("year") String year) {
        return service.getExpYearByYear(year);
    }



}
