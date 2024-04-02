package com.example.demo.Perzistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

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

    @ManyToOne
    private UserEntity user; //UserEntity

    @ManyToOne
    private CalendarEntity calendar; //CalendarEntity

    @OneToMany(mappedBy="toDoListEntities")
    private Set<ItemEntity> item; //ItemEntity

    //@OneToMany(mappedBy = "toDoList")
    //private Set<ItemEntity> itemEntities;


}
