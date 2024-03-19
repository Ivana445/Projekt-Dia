package com.example.demo.Perzistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy="user")
    private Set<ToDoListEntity> toDoListEntities;

}
