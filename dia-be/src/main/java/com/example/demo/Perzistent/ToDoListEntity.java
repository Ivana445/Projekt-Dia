package com.example.demo.Perzistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class ToDoListEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //private String items;

    private Date deadline;

    @ManyToMany
    @JoinTable(
            name = "todo_list_users",
            joinColumns = @JoinColumn(name = "todo_list_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> users = new HashSet<>();

//    @ManyToMany
//    private Set<UserEntity> userEntities; //UserEntity


    //@OneToMany(mappedBy="toDoListEntities")
    //private Set<ItemEntity> item; //ItemEntity


    @OneToMany(mappedBy = "toDoListEntities", cascade = CascadeType.ALL)
    private List<ItemEntity> items = new LinkedList<ItemEntity>();


}
