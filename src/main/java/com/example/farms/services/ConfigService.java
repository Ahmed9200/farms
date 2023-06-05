package com.example.farms.services;

import com.example.farms.DTO.Add.AddCategoryDTO;
import com.example.farms.DTO.Update.UpdateCategoryRequest;
import com.example.farms.models.entities.Categories;
import com.example.farms.models.entities.Configs;
import com.example.farms.models.enums.Constants;
import com.example.farms.repositories.CategoriesRepository;
import com.example.farms.repositories.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ConfigService {

    @Autowired
    ConfigRepository repository;

    public String getValueByName(String name) {
        Optional<Configs> configs = repository.findByName(name);
        if (configs.isPresent()){
            return  configs.get().getValue();
        }else {
            return "0";
        }
    }

    public Object saveVat(int value){
        Map<Object,Object> res = new HashMap<>();
        repository.updateValue("vat",value);
        reloadConfigs();
        res.put("status","success");
        return res;
    }

    public Object saveDelivery(int value){
        Map<Object,Object> res = new HashMap<>();
        repository.updateValue("delivery",value);
        reloadConfigs();
        res.put("status","success");
        return res;
    }

    public void reloadConfigs(){
        Constants.DELIVERY = Integer.parseInt(getValueByName("delivery"));
        Constants.VAT = Integer.parseInt(getValueByName("vat"));
    }
}