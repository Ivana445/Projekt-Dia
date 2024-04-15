package com.example.demo.Perzistent;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter//gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class ToDoListEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Date deadline;

    @ManyToOne
    private UserEntity user; //UserEntity


    @OneToMany(mappedBy="toDoListEntities")
    private Set<ItemEntity> item; //ItemEntity

}
