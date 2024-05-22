package com.example.demo.Perzistent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByemail(String email);

    Optional<UserEntity> findByUsername(String username);


    Optional<UserEntity> findByEmail(String email);

    Set<UserEntity> findByTodoListsId(Long ToDoListId);
}
