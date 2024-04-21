package com.example.demo;

import com.example.demo.Security_core.Service2.AuthenticationService;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    private final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/api/todolist")
    public Long postToDoList(@RequestBody ToDoListDTO toDoListDTO, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        if (authentication.isPresent()){
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.authenticate(token);
            return toDoListService.postToDoList(toDoListDTO, token);
        }else {
            throw new IllegalArgumentException("Chyba v autentifikácii");
        }
    }
    @GetMapping("/api/todolist/{id}")
    public ToDoListDTO getToDoListPodlaId(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            return toDoListService.getToDoListPodlaId(id, token);
        } else {
            throw new IllegalArgumentException("Chyba v autentifikácii");
        }
    }

    @PutMapping("/api/todolist/{id}")
    public void putToDoList(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            toDoListService.putToDoList(id, toDoListDTO, token);
        } else {
            throw new IllegalArgumentException("Chyba v autentifikácii");
        }
    }

    @DeleteMapping("/api/todolist/{id}")
    public void deleteToDoList(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            toDoListService.deleteToDoList(id, token);
        } else {
            throw new IllegalArgumentException("Chyba v autentifikácii");
        }
    }


}
