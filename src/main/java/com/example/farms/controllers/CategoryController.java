package com.example.farms.controllers;

import com.example.farms.DTO.Add.AddCategoryDTO;
import com.example.farms.DTO.Update.UpdateCategoryRequest;
import com.example.farms.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping(value = "/saveCategory", produces = {"application/json"})
    @ResponseBody
    public Object saveCategory(@Valid @RequestBody AddCategoryDTO request) {
        return categoryService.saveCategory(request);
    }

    @PostMapping(value = "/updateCategory", produces = {"application/json"})
    @ResponseBody
    public Object updateCategory(@Valid@RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategory(request);
    }

    @PostMapping(value = "/deleteCategory/{categoryId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteCategory(@PathVariable("categoryId") int categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping(value = "/viewAllCategories", produces = {"application/json"})
    @ResponseBody
    public Object viewAllCategories() {
        return categoryService.viewAllCategories();
    }

    @GetMapping(value = "/getCategoryById/{catId}", produces = {"application/json"})
    @ResponseBody
    public Object getCategoryById(@PathVariable("catId") int catId) {
        return categoryService.getCategoryById(catId);
    }

}
