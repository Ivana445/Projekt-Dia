package com.example.demo.Service;


import com.example.demo.Perzistent.*;
import com.example.demo.Security_core.Perzistent2.TokenEntity;
import com.example.demo.Security_core.Perzistent2.TokenRepository;
import com.example.demo.Security_core.Service2.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ToDoListRepository todoListRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("hasRole('ROLE_USER')")
    public Long postItem(Long toDoListId, ItemDTO itemDTO, String token) {

        ItemEntity entity = new ItemEntity();
        entity.setName(itemDTO.getName());
        entity.setPopis(itemDTO.getPopis());

        ToDoListEntity toDoListEntity = new ToDoListEntity();
        toDoListEntity.setId(toDoListId);
        entity.setToDoListEntities(toDoListEntity);

        ItemEntity savedItemEntity = itemRepository.save(entity);

        return savedItemEntity.getId();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public ItemDTO getItemById(Long itemId, String token) {

        ItemEntity entity = itemRepository.findById(itemId).orElse(null);
        if (entity != null) {
            ItemDTO dto = new ItemDTO();
            dto.setName(entity.getName());
            dto.setPopis(entity.getPopis());
            return dto;
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ItemDTO> getItemsByToDoList(Long toDoListId, String token) {
        Optional<TokenEntity> optionalToken = tokenRepository.findByToken(token);
        if (!optionalToken.isPresent()) {
            throw new IllegalArgumentException("Invalid token provided");
        }
        List<ItemDTO> itemDTOS = new ArrayList<>();

        //Optional<ToDoListEntity> toDoListEntity = todoListRepository.findById(toDoListId);

        Set<ItemEntity> itemEntities = itemRepository.findByToDoListEntitiesId(toDoListId);

        for (ItemEntity itemEntity : itemEntities) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName(itemEntity.getName());
            itemDTO.setPopis(itemEntity.getPopis());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void putItem(Long itemId, ItemDTO itemDTO, String token) {

        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item neexistuje"));

        itemEntity.setName(itemDTO.getName());
        itemEntity.setPopis(itemDTO.getPopis());

        itemRepository.save(itemEntity);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteItem(Long itemId, String token) {

        if (!itemRepository.existsById(itemId)) {
            throw new IllegalArgumentException("Item neexistuje");
        }
        itemRepository.deleteById(itemId);
    }

}
