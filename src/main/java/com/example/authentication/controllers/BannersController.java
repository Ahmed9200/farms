package com.example.authentication.controllers;

import com.example.authentication.requests.Add.AddBannerRequest;
import com.example.authentication.requests.Update.UpdateBannerRequest;
import com.example.authentication.services.BannersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banner")
public class BannersController {

    @Autowired
    private BannersService bannersService;


    @PostMapping(value = "/saveBanner", produces = {"application/json"})
    @ResponseBody
    public Object saveBanner(@RequestBody AddBannerRequest request) {
        return bannersService.saveBanner(request);
    }

    @PutMapping(value = "/updateBanner", produces = {"application/json"})
    @ResponseBody
    public Object updateBanner(@RequestBody UpdateBannerRequest request) {
        return bannersService.updateBanner(request);
    }

    @DeleteMapping(value = "/deleteBanner/{bannerId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteBanner(@PathVariable("bannerId") int categoryId) {
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
