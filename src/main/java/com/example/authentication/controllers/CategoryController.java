package com.example.authentication.controllers;

import com.example.authentication.requests.Add.AddCategoryRequest;
import com.example.authentication.requests.Update.UpdateCategoryRequest;
import com.example.authentication.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping(value = "/saveCategory", produces = {"application/json"})
    @ResponseBody
    public Object saveCategory(@RequestBody AddCategoryRequest request) {
        return categoryService.saveCategory(request);
    }

    @PutMapping(value = "/updateCategory", produces = {"application/json"})
    @ResponseBody
    public Object updateCategory(@RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategory(request);
    }

    @DeleteMapping(value = "/deleteCategory/{categoryId}", produces = {"application/json"})
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
