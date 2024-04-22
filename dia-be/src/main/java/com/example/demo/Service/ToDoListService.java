package com.example.demo.Service;

import com.example.demo.Perzistent.*;
import com.example.demo.Security_core.Perzistent2.RoleRepository;
import com.example.demo.Security_core.Service2.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository todoListRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("hasRole('ROLE_USER')")
    public Long postToDoList(ToDoListDTO toDoListDTO, String token) {

        ToDoListEntity entity = new ToDoListEntity();
        entity.setName(toDoListDTO.getName());
        entity.setDeadline(toDoListDTO.getDeadline());

        ToDoListEntity savedEntity = todoListRepository.save(entity);
        return savedEntity.getId();
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    public ToDoListDTO getToDoListPodlaId(Long id, String token) {


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

    @PreAuthorize("hasRole('ROLE_USER')")
    public void putToDoList(Long id, ToDoListDTO toDoListDTO, String token) {


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

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteToDoList(Long id, String token) {


        Optional<ToDoListEntity> opt = todoListRepository.findById(id);
        if (opt.isPresent()) {
            ToDoListEntity entity = opt.get();
            todoListRepository.delete(entity);
        }
    }


}
