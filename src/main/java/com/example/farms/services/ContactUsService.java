package com.example.farms.services;

import com.example.farms.models.ContactUs;
import com.example.farms.repositories.ContactUsRepository;
import com.example.farms.DTO.Add.AddContactUsDTO;
import com.example.farms.DTO.Update.UpdateContactUsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactUsService {

    @Autowired
    ContactUsRepository contactUsRepository;

    public Map<Object, Object> saveContactUs(AddContactUsDTO request){
        Map<Object,Object> res = new HashMap<>();
        try{
            ContactUs contactUs = new ContactUs(request);
            contactUs = contactUsRepository.save(contactUs);
            res.put("contactUs",contactUs);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object updateContactUs(UpdateContactUsRequest request) {
        Map<Object,Object> res = new HashMap<>();
        try{
            if (contactUsRepository.existsById(request.getContactId())){
                ContactUs contactUs = new ContactUs(request);
                contactUs = contactUsRepository.save(contactUs);
                res.put("contactUs",contactUs);
                res.put("status","success");
            }else{
                res.put("status","error");
                res.put("error","CONT-REP-001"); // contact not exist
            }

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object deleteContactUs(int contactId) {

        Map<Object,Object> res = new HashMap<>();
        try{
            contactUsRepository.deleteById(contactId);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object viewAllContactUs() {
        Map<Object,Object> res = new HashMap<>();
        List<ContactUs> list = contactUsRepository.findAll();
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","BAN-REP-002");//empty list
            return res;
        }
        res.put("contacts",list);
        res.put("total",contactUsRepository.count());
        res.put("status","success");
        return res;
    }

    public Object getContactById(int contactId) {
        Map<Object,Object> res = new HashMap<>();
        Optional<ContactUs> contact = contactUsRepository.findById(contactId);
        if (contact.isPresent()){
            res.put("status","success");
            res.put("contact",contact.get());
        }else {
            res.put("status", "error");
            res.put("error", "CONT-REP-001");//contact not found
        }
        return res;
    }

    public Object getContactByName(String name) {
        Map<Object,Object> res = new HashMap<>();
        List<ContactUs> list = contactUsRepository.findAllByUsernameLike("%"+name+"%");
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","CONT-REP-002");//empty list
            return res;
        }
        res.put("contacts",list);
        res.put("total",contactUsRepository.countAllByUsernameLike("%"+name+"%"));
        res.put("status","success");

        return res;
    }

    public Object getContactByPhone(String phone) {
        Map<Object,Object> res = new HashMap<>();
        List<ContactUs> list = contactUsRepository.findAllByUserPhoneLike("%"+phone+"%");
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","CONT-REP-002");//empty list
            return res;
        }
        res.put("contacts",list);
        res.put("total",contactUsRepository.countAllByUserPhoneLike("%"+phone+"%"));
        res.put("status","success");

        return res;
    }

}