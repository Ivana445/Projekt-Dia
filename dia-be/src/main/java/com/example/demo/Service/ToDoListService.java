package com.example.demo.Service;

import com.example.demo.Perzistent.ToDoListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Perzistent.ToDoListRepository;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository TodoListRepository;

    public Long vytvorToDoList(ToDoListDTO toDoListDTO){
        //ToDo
        return null;
    }
    public ToDoListDTO ziskajToDoListPodlaId(Long id){
        //ToDo
        return null;
    }
    public void upravToDoList(Long id, ToDoListDTO toDoListDTO){
        //ToDo
    }
    public void zmazToDoList(Long id){
        //ToDo
    }
    public ItemDTO pridajPolozku(ItemDTO itemDTO){
        return null;
    }
}
