package com.example.farms.services;

import com.example.farms.models.entities.Banners;
import com.example.farms.repositories.BannersRepository;
import com.example.farms.DTO.Add.AddBannerDTO;
import com.example.farms.DTO.Update.UpdateBannerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BannersService {

    @Autowired
    BannersRepository repository;

    public Map<Object, Object> saveBanner(AddBannerDTO request){
        Map<Object,Object> res = new HashMap<>();
        try{
            Banners banner = new Banners(request);
            banner = repository.save(banner);
            res.put("banner",banner);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object updateBanner(UpdateBannerRequest request) {
        Map<Object,Object> res = new HashMap<>();
        try{
            Banners banners = new Banners(request);
            banners = repository.save(banners);
            res.put("banner",banners);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object deleteBanner(int bannerId) {

        Map<Object,Object> res = new HashMap<>();
        try{
            repository.deleteById(bannerId);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object viewAllBanners() {
        Map<Object,Object> res = new HashMap<>();
        List<Banners> list = repository.findAll();
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","BAN-REP-002");//empty list
            return res;
        }
        res.put("banners",list);
        res.put("total",repository.count());
        res.put("status","success");
        return res;
    }

    public Object getBannerById(int banId) {
        Map<Object,Object> res = new HashMap<>();

        Optional<Banners> ban = repository.findById(banId);
        if (ban.isPresent()){
            res.put("status","success");//category not found
            res.put("banner",ban.get());
        }else {
            res.put("status", "error");
            res.put("error", "BAN-REP-001");//category not found
        }
        return res;
    }

}