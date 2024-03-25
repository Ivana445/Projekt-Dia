package com.example.demo.Service;

import com.example.demo.Perzistent.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository todoListRepository;

    /*public Long vytvorToDoList(ToDoListDTO toDoListDTO) {
        ToDoListEntity entity = new ToDoListEntity();
        entity.setName(toDoListDTO.getName());
        entity.setUser(toDoListDTO.getUser());
        entity.setCalendar(toDoListDTO.getCalendar());
        entity.setItems("items");

        ToDoListEntity savedEntity = todoListRepository.save(entity);
        return savedEntity.getId();
    }*/
    public ToDoListDTO ziskajToDoListPodlaId(Long id){
        ToDoListEntity entity = todoListRepository.findById(id).orElse(null);
        if (entity != null) {
            ToDoListDTO dto = new ToDoListDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            return dto;
        }
        return null;
    }
    public void upravToDoList(Long id, ToDoListDTO toDoListDTO){
        ToDoListEntity entity = todoListRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setName(toDoListDTO.getName());
            todoListRepository.save(entity);
        }
    }
    public void zmazToDoList(Long id){
        todoListRepository.deleteById(id);
    }
    public ItemDTO pridajPolozku(ItemDTO itemDTO){
        return null;
    }
}
