package com.example.authentication.services;

import com.example.authentication.DamhaApplication;
import com.example.authentication.repositories.configRepo.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigService {

@Autowired
ConfigRepository configRepository;




    public HashMap<String, String> loadConfigurations() {

        HashMap<String, String> hashmap = new HashMap<String, String>();
        try {
        List<Map<String,String>> conf = configRepository.getAllConfigurations();
        for (Map<String, String> map : conf) {
            hashmap.put(map.get("name"), map.get("content"));
        }
        }catch (Exception e){
            System.err.println("there is some configs are missing please check it out !!");
        }

        // putting the important configs if there not exist in first of project load constants

        if (!hashmap.containsKey("TIME_FOR_CLOSE_COMPLAINTS"))
            hashmap.put("TIME_FOR_CLOSE_COMPLAINTS","3");


        return hashmap;
    }


    public boolean reloadConfigs(){
        try {
            DamhaApplication.CONSTANTS_MAP = loadConfigurations();
            System.err.println(DamhaApplication.CONSTANTS_MAP);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


}
