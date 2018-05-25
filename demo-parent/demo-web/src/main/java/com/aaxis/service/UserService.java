package com.aaxis.service;

import com.aaxis.pojo.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(Long id);

    User findByUsername(String userName);

    User findByUsernameIgnoreCase(String userName);

    User findByEmailIgnoreCase(String email);

    void deleteById(Long id);

    void deleteAll();

    void delete(User user);

    void save(User user);

    User getOne(Long id);


}
