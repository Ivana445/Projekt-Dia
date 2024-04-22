package com.example.demo;


import com.example.demo.Service.UserDTO;
import com.example.demo.Service.UserService;
import com.example.demo.Security_core.Service2.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static com.example.demo.Security_core.Controller.AuthenticationController.credentialsDecode;

@RestController
@CrossOrigin("*")
public class UserController {
    private final String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/api/registration")
    public void UserRegistration(@RequestBody UserDTO userDTO, HttpServletResponse response){
        String token = userService.registerUser(userDTO);
        response.setStatus(HttpStatus.OK.value());
        response.addHeader("Authorization", "Bearer " + token);
    }

    @GetMapping("/api/registration/{id}")
    public UserDTO GetRegistration(@PathVariable Long id){
        return userService.GetRegistration(id);
    }

    @PostMapping("/api/login")
    public void PostLogin(@RequestHeader(value = "Authorization", required = false) Optional<String> authentication,
                            HttpServletResponse response){

        String[] credentials = credentialsDecode(authentication.get());
        String token = authenticationService.authenticate(credentials[0], credentials[1]);
        response.setStatus(HttpStatus.OK.value());
        response.addHeader("Authorization", "Bearer " + token);
    }
    @GetMapping("api/login/{id}")
    public UserDTO GetLogin(@PathVariable Long id /*@RequestHeader("Authorization") String token*/){
        return userService.GetLogin(id); /*token*/
    }
    @PutMapping("/api/change/{id}")
    public void ChangePassword(@PathVariable Long id, @RequestBody UserDTO userDTO, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication){
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.authenticate(token);
            userService.ChangePassword(id, userDTO);
        } else {
            throw new IllegalArgumentException("neda sa");
        }
    }
    @GetMapping("api/change/{id}")
    public String GetPassword(@PathVariable Long id, @RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication){
        if (authentication.isPresent()){
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.authenticate(token);
            return userService.GetPassword(id, token);
        }else {
            throw new IllegalArgumentException("neda sa");
        }

    }
    @DeleteMapping("/api/logoff")
    public void logoff(@RequestHeader(value = "Authorization", required = true) Optional<String> authentication) {
        if (authentication.isPresent()) {
            String token = authentication.get().substring("Bearer".length()).trim();
            authenticationService.tokenRemove(token);
        }else {
            throw new IllegalArgumentException("neda sa");
        }
    }


}
