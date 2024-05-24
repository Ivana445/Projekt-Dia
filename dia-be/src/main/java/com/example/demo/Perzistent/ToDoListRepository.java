package com.example.demo.Perzistent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoListEntity, Long> {
    //Long je typ primarneho kluca s ktorym kniha pracuje

    Set<ToDoListEntity> findByUsersId(Long id);
}
