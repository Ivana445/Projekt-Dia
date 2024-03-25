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
    public Long vytvorToDoList(@RequestBody ToDoListDTO toDoListDTO){
        return null; //toDoListService.vytvorToDoList(toDoListDTO);
    }
    @PostMapping("/api/todolist/{id}")
    public Long pridajPolozku(@PathVariable Long id, @RequestBody ItemDTO itemDTO){
        //ToDo
        return null;
    }
    @GetMapping("/api/todolist/{id}")
    public ToDoListDTO ziskajToDoListPodlaId(@PathVariable Long id){
        return toDoListService.ziskajToDoListPodlaId(id);
    }
    @PutMapping("/api/todolist/{id}")
    public void upravToDoList(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO){
        toDoListService.upravToDoList(id, toDoListDTO);
    }
    @DeleteMapping("/api/todolist/{id}")
    public void vymazToDoList(@PathVariable Long id){
        toDoListService.zmazToDoList(id);
    }

}
