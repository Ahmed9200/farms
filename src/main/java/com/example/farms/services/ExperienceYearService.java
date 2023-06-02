package com.example.farms.services;

import com.example.farms.models.entities.ExperiencesYears;
import com.example.farms.repositories.ExperiencesYearsRepository;
import com.example.farms.DTO.Add.AddExpYearDTO;
import com.example.farms.DTO.Update.UpdateExpYearRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExperienceYearService {

    @Autowired
    ExperiencesYearsRepository repository;



    public Map<Object, Object> saveExpYear(AddExpYearDTO request){
        Map<Object,Object> res = new HashMap<>();
        try{
            ExperiencesYears experiencesYear = new ExperiencesYears(request);
            experiencesYear = repository.save(experiencesYear);
            res.put("expYear",experiencesYear);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object updateExpYear(UpdateExpYearRequest request) {
        Map<Object,Object> res = new HashMap<>();
        try{
            ExperiencesYears experiencesYear = new ExperiencesYears(request);
            experiencesYear = repository.save(experiencesYear);
            res.put("expYear",experiencesYear);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object deleteExpYear(int partnerId) {

        Map<Object,Object> res = new HashMap<>();
        try{
            repository.deleteById(partnerId);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object viewAllExpYear() {
        Map<Object,Object> res = new HashMap<>();
        List<ExperiencesYears> list = repository.findAll();
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","EXP-REP-002");//empty list
            return res;
        }
        res.put("expYears",list);
        res.put("total",repository.count());
        res.put("status","success");
        return res;
    }

    public Object getExpYearById(int partnerId) {
        Map<Object,Object> res = new HashMap<>();

        Optional<ExperiencesYears> experiencesYear = repository.findById(partnerId);
        if (experiencesYear.isPresent()){
            res.put("status","success");
            res.put("expYear",experiencesYear.get());
        }else {
            res.put("status", "error");
            res.put("error", "EXP-REP-001");//category not found
        }
        return res;
    }

    public Object getExpYearByYear(String year) {
        Map<Object,Object> res = new HashMap<>();
        List<ExperiencesYears> list = repository.findAllByYearLike(year);
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","BAN-REP-002");//empty list
            return res;
        }
        res.put("expYears",list);
        res.put("total",repository.count());
        res.put("status","success");

        return res;
    }

}