package com.example.demo.Perzistent;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter//gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class ItemEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private ToDoListEntity toDoListEntities;

}
