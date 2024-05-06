package com.example.demo.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data //gettre settre to string...
@NoArgsConstructor //konstruktor
@AllArgsConstructor //automaticky doplni parametricky konstruktor
public class ToDoListDTO {
    Long id;
    String name;
    Date deadline;

    List<ItemDTO> items = new ArrayList<>();


    //Day notification
    //Hour notification
    //Repeating notification
    //Share
}
