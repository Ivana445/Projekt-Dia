package com.example.demo.Perzistent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
}
