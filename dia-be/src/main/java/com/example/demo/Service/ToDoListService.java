package com.example.demo.Service;

import com.example.demo.Perzistent.ToDoListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Perzistent.ToDoListRepository;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository TodoListRepository;

//    public Long vytvorToDoList(ToDoListDTO toDoListDTO){
//        ToDoListEntity entity = new ToDoListEntity();
//        entity.setName(entity.getName());
//        entity.setItems(entity.getItems());
//        ToDoListRepository.save(entity);
//        return entity.getId();
//    }
//    public ToDoListDTO ziskajToDoListPodlaId(ToDoListDTO toDoListDTO){
//
//    }
}
