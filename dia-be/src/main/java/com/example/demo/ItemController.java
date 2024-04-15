package com.example.demo;

import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
//@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/api/todolist/{todolistId}/items")
    public Long createItem(@PathVariable("todolistId") Long todolistId, @RequestBody ItemDTO itemDTO) {
        return itemService.postItem(todolistId, itemDTO);
    }

    @GetMapping("/api/item/{id}")
    public ItemDTO getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }
    @PutMapping("/api/item/{id}")
    public void putItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO){
        itemService.putItem(id,itemDTO);
    }
    @DeleteMapping("/api/item/{id}")
    public void deleteToDoList(@PathVariable Long id){
        itemService.deleteItem(id);
    }

}
