package com.example.authentication.controllers;

import com.example.authentication.requests.Add.AddExpYearRequest;
import com.example.authentication.requests.Update.UpdateExpYearRequest;
import com.example.authentication.services.ExperienceYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exp_years")
public class ExperienceYearController {

    @Autowired
    private ExperienceYearService service;


    @PostMapping(value = "/saveExpYear", produces = {"application/json"})
    @ResponseBody
    public Object saveExpYear(@RequestBody AddExpYearRequest request) {
        return service.saveExpYear(request);
    }

    @PutMapping(value = "/updateExpYear", produces = {"application/json"})
    @ResponseBody
    public Object updateExpYear(@RequestBody UpdateExpYearRequest request) {
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
