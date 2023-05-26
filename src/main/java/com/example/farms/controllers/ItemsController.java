package com.example.farms.controllers;

import com.example.farms.DTO.Add.AddItemDTO;
import com.example.farms.DTO.Update.UpdateItemDTO;
import com.example.farms.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;


    @PostMapping(value = "/saveItem", produces = {"application/json"})
    @ResponseBody
    public Object saveItem(@Valid @RequestBody AddItemDTO request) {
        return itemsService.saveItem(request);
    }

    @PutMapping(value = "/updateItem", produces = {"application/json"})
    @ResponseBody
    public Object updateItem(@Valid@RequestBody UpdateItemDTO request) {
        return itemsService.updateItem(request);
    }

    @DeleteMapping(value = "/deleteItem/{itemId}", produces = {"application/json"})
    @ResponseBody
    public Object deleteItem(@Valid@PathVariable("itemId") int itemId) {
        return itemsService.deleteItem(itemId);
    }

    @GetMapping(value = "/getItemById/{itemId}", produces = {"application/json"})
    @ResponseBody
    public Object getItemById(@PathVariable("itemId") int itemId) {
        return itemsService.getItemById(itemId);
    }

    @GetMapping(params = { "page", "size" },
            value = "/getItemByNameLike/{name}",
            produces = {"application/json"})
    @ResponseBody
    public Object getItemByNameLike(
            @PathVariable("name") String name,
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size) {
        return itemsService.getItemByNameLike(name,page,size);
    }

    @GetMapping(params = { "page", "size" ,"sortBy"},
            value = "/getItemByCategory/{categoryId}",
            produces = {"application/json"})
    @ResponseBody
    public Object getItemByCategory(
            @PathVariable("categoryId") int categoryId,
            @RequestParam(value = "sortBy", defaultValue = "id" , required = false) String sortBy,
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size) {
        return itemsService.getItemByCategory(categoryId,page,size,sortBy);
    }


    @GetMapping(params = { "page", "size" ,"sortBy"},
            value = "/allSaleItems",
            produces = {"application/json"})
    @ResponseBody
    public Object allSaleItems(
            @RequestParam(value = "sortBy", defaultValue = "id" , required = false) String sortBy,
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size) {
        return itemsService.allSaleItems(page,size,sortBy);
    }

    @GetMapping(params = { "page", "size" ,"sortBy"},
            value = "/allNewItems",
            produces = {"application/json"})
    @ResponseBody
    public Object allNewItems(
            @RequestParam(value = "sortBy", defaultValue = "id" , required = false) String sortBy,
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size) {
        return itemsService.allNewItems(page,size,sortBy);
    }

    @GetMapping(params = { "page", "size" ,"sortBy"},
            value = "/allItems",
            produces = {"application/json"})
    @ResponseBody
    public Object allItems(
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10000" , required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "id" , required = false) String sortBy) {
        return itemsService.allItems(page,size,sortBy);
    }



}
