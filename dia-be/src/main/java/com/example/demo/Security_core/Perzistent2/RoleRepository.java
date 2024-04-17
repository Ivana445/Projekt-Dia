package com.example.demo.Security_core.Perzistent2;


import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

RoleEntity findByRoleName(String role_name);

}
