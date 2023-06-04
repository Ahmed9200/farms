package com.example.farms.controllers;

import com.example.farms.DTO.Add.AddBannerDTO;
import com.example.farms.DTO.Update.UpdateBannerRequest;
import com.example.farms.services.BannersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/banner")
public class BannersController {

    @Autowired
    private BannersService bannersService;


    @PostMapping(value = "/saveBanner", produces = {"application/json"})
    @ResponseBody
    public Object saveBanner(@Valid @RequestBody AddBannerDTO request) {
        return bannersService.saveBanner(request);
    }

    @PostMapping(value = "/updateBanner", produces = {"application/json"})
    @ResponseBody
    public Object updateBanner(@Valid@RequestBody UpdateBannerRequest request) {
        return bannersService.updateBanner(request);
    }

    @PostMapping(value = "/deleteBanner/{bannerId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteBanner(@Valid@PathVariable("bannerId") int categoryId) {
        return bannersService.deleteBanner(categoryId);
    }

    @GetMapping(value = "/viewAllBanners", produces = {"application/json"})
    @ResponseBody
    public Object viewAllBanners() {
        return bannersService.viewAllBanners();
    }

    @GetMapping(value = "/getBannerById/{bannerId}", produces = {"application/json"})
    @ResponseBody
    public Object getBannerById(@PathVariable("bannerId") int catId) {
        return bannersService.getBannerById(catId);
    }


}
