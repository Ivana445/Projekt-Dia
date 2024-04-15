package com.example.demo.Service;


import com.example.demo.Perzistent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ToDoListRepository toDoListRepository;

    public Long postItem(Long toDoListId, ItemDTO itemDTO) {

        ItemEntity entity = new ItemEntity();
        entity.setName(itemDTO.getName());
        entity.setPopis(itemDTO.getPopis());

        ToDoListEntity toDoListEntity = new ToDoListEntity();
        toDoListEntity.setId(toDoListId);
        entity.setToDoListEntities(toDoListEntity);

        ItemEntity savedItemEntity = itemRepository.save(entity);

        return savedItemEntity.getId();
    }

    public ItemDTO getItemById(Long itemId) {
        ItemEntity entity = itemRepository.findById(itemId).orElse(null);
        if (entity != null){
            ItemDTO dto = new ItemDTO();
            dto.setName(entity.getName());
            dto.setPopis(entity.getPopis());
            return dto;
        }
        return null;
    }

    public void putItem(Long itemId, ItemDTO itemDTO) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item neexistuje"));

        itemEntity.setName(itemDTO.getName());
        itemEntity.setPopis(itemDTO.getPopis());

        itemRepository.save(itemEntity);
    }

    public void deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalArgumentException("Item neexistuje");
        }
        itemRepository.deleteById(itemId);
    }
}
