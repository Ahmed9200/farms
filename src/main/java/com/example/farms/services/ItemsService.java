package com.example.farms.services;

import com.example.farms.DTO.Add.AddItemDTO;
import com.example.farms.DTO.Update.UpdateItemDTO;
import com.example.farms.models.entities.Items;
import com.example.farms.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemsService {

    @Autowired
    ItemsRepository itemsRepository;

    public Map<Object, Object> saveItem(AddItemDTO request){
        Map<Object,Object> res = new HashMap<>();
        try{
            Items item = new Items(request);
            item = itemsRepository.save(item);
            res.put("item",item);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object updateItem(UpdateItemDTO request) {
        Map<Object,Object> res = new HashMap<>();
        try{
            if (itemsRepository.existsById(request.getItemId())){
                Items item = new Items(request);
                item = itemsRepository.save(item);
                res.put("item",item);
                res.put("status","success");
            }else {
                res.put("status","error");
                res.put("error","ITEM-REP-001"); // item not exist
            }

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
            res.put("error",e.getMessage());
        }
        return res;
    }

    public Object deleteItem(int itemId) {

        Map<Object,Object> res = new HashMap<>();
        try{
            itemsRepository.deleteById(itemId);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object getItemById(int itemId) {
        Map<Object,Object> res = new HashMap<>();

        Optional<Items> item = itemsRepository.findById(itemId);
        if (item.isPresent()){
            res.put("status","success");//category not found
            res.put("item",item.get());
        }else {
            res.put("status", "error");
            res.put("error", "ITEM-REP-001");//item not found
        }
        return res;
    }

    public Object getItemByNameLike(String name , int page,int size) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size,Sort.by(Sort.Direction.DESC,"addedDate"));
        Page<Items> items= itemsRepository.findAllByNameLike("%"+name+"%",pageable);
        res.put("total",items.getTotalElements());
        res.put("items",items.get());
        return res;
    }

    public Object getItemByCategory(int catId,int page,int size,String sortBy) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size,Sort.by(Sort.Direction.DESC,sortBy));
        Page<Items> items= itemsRepository.findAllByCategoryId(catId,pageable);
        res.put("total",items.getTotalElements());
        res.put("items",items.get());
        return res;
    }

    public Object allSaleItems(int page,int size,String sortBy) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sortBy));
        Page<Items> items= itemsRepository.findAllBySaleGreaterThan(0,pageable);
        res.put("total",items.getTotalElements());
        res.put("items",items.get());
        return res;
    }


    public Object allNewItems(int page,int size,String sortBy) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sortBy));
        Page<Items> items= itemsRepository.findAllByNewItemsIsTrue(pageable);
        res.put("total",items.getTotalElements());
        res.put("items",items.get());
        return res;
    }

    public Object allItems(int page, int size,String sortBy) {
        Map<Object,Object> res = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sortBy));
        Page<Items> items= itemsRepository.findAll(pageable);
        res.put("total",items.getTotalElements());
        res.put("items",items.get());
        return res;
    }
}