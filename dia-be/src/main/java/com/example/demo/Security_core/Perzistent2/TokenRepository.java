package com.example.demo.Security_core.Perzistent2;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);
    Long deleteByToken(String token);
}
