package com.example.farms.services;

import com.example.farms.DTO.Add.AddItemDTO;
import com.example.farms.DTO.Update.UpdateItemDTO;
import com.example.farms.models.Items;
import com.example.farms.models.Partners;
import com.example.farms.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Pageable pageable = PageRequest.of(page, size);
        return itemsRepository.findAllByNameLike("%"+name+"%",pageable);
    }

    public Object getItemByCategory(int catId,int page,int size,String sortBy) {
        Pageable pageable = PageRequest.of(page, size,Sort.by(sortBy));
        return itemsRepository.findAllByCategoryId(catId,pageable);
    }

    public Object allSaleItems(int page,int size,String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return itemsRepository.findAllBySaleGreaterThan(0,pageable);
    }


    public Object allNewItems(int page,int size,String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return itemsRepository.findAllByNewItemsIsTrue(pageable);
    }

    public Object allItems(int page, int size,String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return itemsRepository.findAll(pageable);
    }
}