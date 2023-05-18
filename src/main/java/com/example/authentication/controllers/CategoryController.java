package com.example.authentication.controllers;

import com.example.authentication.models.AppUser;
import com.example.authentication.models.Users;
import com.example.authentication.requests.AddCategoryRequest;
import com.example.authentication.requests.LimitAndOffsetRequest;
import com.example.authentication.requests.UpdateCategoryRequest;
import com.example.authentication.requests.userRequests.*;
import com.example.authentication.responses.JwtResponse;
import com.example.authentication.services.CategoryService;
import com.example.authentication.services.PartnersService;
import com.example.authentication.services.TokenService;
import com.example.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
