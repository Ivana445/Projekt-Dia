package com.example.demo;

import com.example.demo.Perzistent.ItemEntity;
import com.example.demo.Security_core.Service2.AuthenticationService;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
        String token = authentication.get().substring("Bearer".length()).trim();
        return toDoListService.postToDoList(toDoListDTO, token);
    }
    @GetMapping("/api/todolist/{id}")
    public ToDoListDTO getToDoListPodlaId(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
            String token = authentication.get().substring("Bearer".length()).trim();
            return toDoListService.getToDoListPodlaId(id, token);
    }

    @GetMapping("/api/todolist/all")
    public List<ToDoListDTO> getAllToDoLists(@RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication){
        String token = authentication.get().substring("Bearer".length()).trim();
        return toDoListService.getAllToDoLists(token);
    }

    @PutMapping("/api/todolist/{id}")
    public void putToDoList(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
            String token = authentication.get().substring("Bearer".length()).trim();
            toDoListService.putToDoList(id, toDoListDTO, token);
    }
    @PutMapping("/api/todolist/change/{id}")
    public void addUser(@PathVariable Long id, @RequestBody UserDTO userDTO, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.authenticate(token);
            toDoListService.addUser(id, userDTO.getEmail());
        } else {
            throw new IllegalArgumentException("neda sa");
        }
    }
    @DeleteMapping("/api/todolist/change/{id}")
    public void deleteUserFromTodolist(@PathVariable Long id, @RequestBody UserDTO userDTO,  @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication){
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.authenticate(token);
            toDoListService.deleteUserFromTodolist(id, userDTO.getEmail());
        }else {
            throw new IllegalArgumentException("neda sa");
        }
    }
    @GetMapping("/api/todolist/change/{id}")
    public ResponseEntity<List<UserDTO>> getUsersList(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication){
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.authenticate(token);
            List<UserDTO> users = toDoListService.findAllUsers(id);
            return ResponseEntity.ok(users);
        }else {
            throw new IllegalArgumentException("neda sa");
        }

    }

    @DeleteMapping("/api/todolist/{id}")
    public void deleteToDoList(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
            String token = authentication.get().substring("Bearer".length()).trim();
            toDoListService.deleteToDoList(id, token);
    }




}
