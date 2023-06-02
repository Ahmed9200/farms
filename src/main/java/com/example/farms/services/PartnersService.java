package com.example.farms.services;

import com.example.farms.models.entities.Partners;
import com.example.farms.repositories.PartnersRepository;
import com.example.farms.DTO.Add.AddPartnerDTO;
import com.example.farms.DTO.Update.UpdatePartnerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PartnersService {

    @Autowired
    PartnersRepository partnersRepository;


    public Map<Object, Object> savePartner(AddPartnerDTO request){
        Map<Object,Object> res = new HashMap<>();
        try{
            Partners partner = new Partners(request);
            partner = partnersRepository.save(partner);
            res.put("partner",partner);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object updatePartner(UpdatePartnerRequest request) {
        Map<Object,Object> res = new HashMap<>();
        try{
            if (partnersRepository.existsById(request.getPartnerId())){
                Partners partner = new Partners(request);
                partner = partnersRepository.save(partner);
                res.put("partner",partner);
                res.put("status","success");
            }else {
                res.put("status","error");
                res.put("error","PART-REP-001"); // partner not exist
            }

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object deletePartner(int partnerId) {

        Map<Object,Object> res = new HashMap<>();
        try{
            partnersRepository.deleteById(partnerId);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object viewAllPartners() {
        Map<Object,Object> res = new HashMap<>();
        List<Partners> list = partnersRepository.findAll();
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","BAN-REP-002");//empty list
            return res;
        }
        res.put("partners",list);
        res.put("total",partnersRepository.count());
        res.put("status","success");
        return res;
    }

    public Object getPartnerById(int partnerId) {
        Map<Object,Object> res = new HashMap<>();

        Optional<Partners> partners = partnersRepository.findById(partnerId);
        if (partners.isPresent()){
            res.put("status","success");//category not found
            res.put("partner",partners.get());
        }else {
            res.put("status", "error");
            res.put("error", "PART-REP-001");//category not found
        }
        return res;
    }

    public Object getPartnerByNameLike(String name) {
        Map<Object,Object> res = new HashMap<>();
        List<Partners> list = partnersRepository.findAllByNameLike("%"+name+"%");
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","BAN-REP-002");//empty list
            return res;
        }
        res.put("partners",list);
        res.put("total",partnersRepository.count());
        res.put("status","success");

        return res;
    }
}