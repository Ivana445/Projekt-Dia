package com.example.demo.Perzistent;

//import com.example.demo.Security_core.Perzistent2.RoleEntity;
import com.example.demo.Security_core.Perzistent2.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

//    @ManyToMany(mappedBy="user")
//    private Set<ToDoListEntity> toDoListEntities;
    @ManyToMany(mappedBy = "users")
    private Set<ToDoListEntity> todoLists = new HashSet<>();

    @ManyToMany
    private Set<RoleEntity> roles = new HashSet<>();

    public Set<ToDoListEntity> getTodoLists() {
        return todoLists;
    }
}
