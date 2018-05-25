package com.aaxis.dao;

import com.aaxis.pojo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Cacheable(cacheNames="user",unless = "#result == null")
    User findByUsername(@Param("username") String username);

    User findByUsernameIgnoreCase(@Param("username") String username);

    User findByEmailIgnoreCase(@Param("email") String email);

    User getOne(Long id);

}
