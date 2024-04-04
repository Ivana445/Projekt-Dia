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

    @PostMapping("/api/login")
    public Long PostLogin(@RequestBody UserDTO userDTO){
        return userService.PostLogin(userDTO);
    }
    @GetMapping("api/login/{id}")
    public UserDTO GetLogin(@PathVariable Long id){
        return userService.GetLogin(id);
    }
    @PutMapping("/api/change/{id}")
    public void ChangePassword(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userService.ChangePassword(id, userDTO);
    }
    @GetMapping("api/change/{id}")
    public String GetPassword(@PathVariable Long id){
        return userService.GetPassword(id);
    }

}
