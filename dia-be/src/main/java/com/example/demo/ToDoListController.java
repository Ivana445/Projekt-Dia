package com.example.demo;

import com.example.demo.Service.ItemDTO;
import com.example.demo.Service.ToDoListService;
import com.example.demo.Service.ToDoListDTO;
import com.example.demo.Service.CalendarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    @PostMapping("/api/todolist")
    public Long postToDoList(@RequestBody ToDoListDTO toDoListDTO, @RequestHeader("userId") Long userId) {
        return toDoListService.postToDoList(userId, toDoListDTO);
    }

    @GetMapping("/api/todolist/{id}")
    public ToDoListDTO getToDoListPodlaId(@PathVariable Long id){
        return toDoListService.getToDoListPodlaId(id);
    }
    @PutMapping("/api/todolist/{id}")
    public void putToDoList(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO){
        toDoListService.putToDoList(id, toDoListDTO);
    }
    @DeleteMapping("/api/todolist/{id}")
    public void deleteToDoList(@PathVariable Long id){
        toDoListService.deleteToDoList(id);
    }

}
