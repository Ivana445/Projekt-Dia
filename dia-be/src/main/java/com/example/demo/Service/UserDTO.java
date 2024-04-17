package com.example.demo.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class UserDTO {

    Long id;
    String username;
    String password;
    String email;
    private Set<String> roles;


    public Set<String> getRoles() {
        return roles;
    }
}
