package com.aaxis.dao;

import com.aaxis.pojo.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends CrudRepository<Role, Long> {
}
