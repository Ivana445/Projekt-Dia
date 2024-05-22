package com.example.demo;

import com.example.demo.Security_core.Service2.AuthenticationService;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;
    private final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/todolist/{todolistId}/items")
    public Long postItem(@PathVariable("todolistId") Long todolistId, @RequestBody ItemDTO itemDTO, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.authenticate(token);
            return itemService.postItem(todolistId, itemDTO, token);
    }

    @GetMapping("/api/item/{id}")
    public ItemDTO getItemById(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
            String token = authentication.get().substring("Bearer".length()).trim();
            return itemService.getItemById(id, token);
    }

    @GetMapping("/api/items/{id}")
    public List<ItemDTO> getItemsByToDoList(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        String token = authentication.get().substring("Bearer".length()).trim();
        return itemService.getItemsByToDoList(id, token);
    }

    @PutMapping("/api/item/{id}")
    public void putItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
            String token = authentication.get().substring("Bearer".length()).trim();
            itemService.putItem(id, itemDTO, token);
    }

    @DeleteMapping("/api/item/{id}")
    public void deleteItem(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
            String token = authentication.get().substring("Bearer".length()).trim();
            itemService.deleteItem(id, token);
    }
}
