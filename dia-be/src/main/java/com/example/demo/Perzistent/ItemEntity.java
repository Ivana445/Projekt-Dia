package com.example.demo.Perzistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class ItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String popis;
    @ManyToOne
    private ToDoListEntity toDoListEntities;

}
