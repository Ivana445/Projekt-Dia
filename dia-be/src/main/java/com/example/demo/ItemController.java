package com.example.demo;

import com.example.demo.Service.ItemService;
import com.example.demo.Service.UserDTO;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
//@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemService itemService;


}
