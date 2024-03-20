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
@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class ItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String popis;
    @OneToMany(mappedBy="item")
    private Set<ToDoListEntity> toDoListEntities;

}
