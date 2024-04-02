package com.example.demo.Service;

import com.example.demo.Perzistent.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository todoListRepository;

    /*public Long postToDoList(ToDoListDTO toDoListDTO) {
        ToDoListEntity entity = new ToDoListEntity();
        entity.setName(toDoListDTO.getName());
        entity.setUser(toDoListDTO.getUser());
        entity.setDeadline(toDoListDTO.getDeadline());
        entity.setCalendar(toDoListDTO.getCalendar());
        entity.setItems("items");

        ToDoListEntity savedEntity = todoListRepository.save(entity);
        return savedEntity.getId();
    }*/
    public ToDoListDTO getToDoListPodlaId(Long id){
        ToDoListEntity entity = todoListRepository.findById(id).orElse(null);
        if (entity != null) {
            ToDoListDTO dto = new ToDoListDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            return dto;
        }
        return null;
    }


    public void putToDoList(Long id, ToDoListDTO toDoListDTO){
        ToDoListEntity entity = todoListRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setName(toDoListDTO.getName());
            todoListRepository.save(entity);
        }
    }

    public void deleteToDoList(Long id){
        todoListRepository.deleteById(id);
    }

}
