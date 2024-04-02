package com.example.demo.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class ToDoListDTO {
    Long id;
    String name;
    Date deadline;

    //ItemDTO items;


    //Day notification
    //Hour notification
    //Repeating notification
    //Share
}
