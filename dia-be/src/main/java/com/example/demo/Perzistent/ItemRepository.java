package com.example.demo.Perzistent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
    Set<ItemEntity> findByToDoListEntitiesId(Long toDoListId);
}
