package com.example.demo;

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



}
