package com.example.farms.services;

import com.example.farms.models.entities.Categories;
import com.example.farms.repositories.CategoriesRepository;
import com.example.farms.DTO.Add.AddCategoryDTO;
import com.example.farms.DTO.Update.UpdateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService{

    @Autowired
    CategoriesRepository categoriesRepository;

    public Map<Object, Object> saveCategory(AddCategoryDTO request){
        Map<Object,Object> res = new HashMap<>();
        try{
            Categories category = new Categories(request);
            category = categoriesRepository.save(category);
            res.put("category",category);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object updateCategory(UpdateCategoryRequest request) {
        Map<Object,Object> res = new HashMap<>();
        try{
            Categories category = new Categories(request);
            category = categoriesRepository.save(category);
            res.put("category",category);
            res.put("status","success");

        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object deleteCategory(int categoryId) {

        Map<Object,Object> res = new HashMap<>();
        try{
            categoriesRepository.deleteById(categoryId);
            res.put("status","success");
        }catch (Exception e){
            e.printStackTrace();
            res.put("status","error");
        }
        return res;
    }

    public Object viewAllCategories() {
        Map<Object,Object> res = new HashMap<>();
        List<Categories> list = categoriesRepository.findAll();
        if (list.size() == 0){
            res.put("status","error");
            res.put("error","CAT-REP-002");//empty list
            return res;
        }
        res.put("categories",list);
        res.put("total",categoriesRepository.count());
        res.put("status","success");
        return res;
    }

    public Object getCategoryById(int catId) {
        Map<Object,Object> res = new HashMap<>();

        Optional<Categories> cat = categoriesRepository.findById(catId);
        if (cat.isPresent()){
            res.put("status","success");//category not found
            res.put("category",cat.get());
        }else {
            res.put("status", "error");
            res.put("error", "CAT-REP-001");//category not found
        }
        return res;
    }
}