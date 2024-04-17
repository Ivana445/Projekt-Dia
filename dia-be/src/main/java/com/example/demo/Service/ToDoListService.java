package com.example.demo.Service;

import com.example.demo.Perzistent.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository todoListRepository;

    @Autowired
    private UserService userService;

//    public Long postToDoList(Long userId, ToDoListDTO toDoListDTO) {
//        if (userId == null) {
//            throw new IllegalArgumentException("User id je potrebne na vytvorenie ToDoListu");
//        }
//        //UserDTO userDTO = userService.GetLogin(userId); //fetch usera z databazy
//        if (userDTO == null) {
//            throw new IllegalArgumentException("User s ID " + userId + " neexistuje");
//        }
//        ToDoListEntity entity = new ToDoListEntity();
//        entity.setName(toDoListDTO.getName());
//        entity.setDeadline(toDoListDTO.getDeadline());
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(userId);
//        entity.setUser(userEntity);
//
//        ToDoListEntity savedEntity = todoListRepository.save(entity);
//        return savedEntity.getId();
//    }
    public ToDoListDTO getToDoListPodlaId(Long id){
        ToDoListEntity entity = todoListRepository.findById(id).orElse(null);
        if (entity != null) {
            ToDoListDTO dto = new ToDoListDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDeadline(entity.getDeadline());
            return dto;
        }
        return null;
    }


    public void putToDoList(Long id, ToDoListDTO toDoListDTO){
        ToDoListEntity entity = todoListRepository.findById(id).orElse(null);
        if (entity != null) {
            if (toDoListDTO.getName() != null) {
                entity.setName(toDoListDTO.getName());
            }
            if (toDoListDTO.getDeadline() != null) {
                entity.setDeadline(toDoListDTO.getDeadline());
            }
            todoListRepository.save(entity);
        }
    }

    public void deleteToDoList(Long id){
        Optional<ToDoListEntity> opt = todoListRepository.findById(id);
        if (opt.isEmpty()) {
            return;
        }
        ToDoListEntity entity = opt.get();
        todoListRepository.delete(entity);

    }

}
