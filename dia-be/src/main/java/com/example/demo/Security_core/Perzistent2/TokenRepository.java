package com.example.demo.Security_core.Perzistent2;

import com.example.demo.Perzistent.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);
    //Optional<UserEntity> findByUser(String token);
    Long deleteByToken(String token);
}
