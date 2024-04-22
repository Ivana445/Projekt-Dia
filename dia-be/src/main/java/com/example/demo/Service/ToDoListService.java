package com.example.demo.Service;

import com.example.demo.Perzistent.*;
import com.example.demo.Security_core.Perzistent2.RoleRepository;
import com.example.demo.Security_core.Service2.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;


@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository todoListRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

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
    public void addUser(Long id, UserDTO userDTO, String token){
        authenticationService.authenticate(token);
        Optional<UserEntity> userOptional = userRepository.findByEmail(userDTO.getEmail());
        ToDoListEntity toDoList = todoListRepository.findById(id).orElse(null);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            System.out.println("userr " + user);
            System.out.println("todio " + toDoList);
            if (toDoList != null) {
                toDoList.getUsers().add(user);
                todoListRepository.save(toDoList);
            }else {
                throw new IllegalArgumentException("neda sa");
            }
        } else {
            throw new IllegalArgumentException("Uživatel s tímto emailem nebyl nalezen.");
        }
    }



}
