package com.example.demo;


import com.example.demo.Service.CalendarDTO;
import com.example.demo.Service.UserDTO;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/api/registration")
    public Long UserRegistration(@RequestBody UserDTO userDTO){
        return userService.userRegistration(userDTO);
    }
    @GetMapping("/api/registration/{id}")
    public UserDTO GetRegistration(@PathVariable Long id){
        return userService.GetRegistration(id);
    }
}
