package com.aaxis.service.impl;

import com.aaxis.dao.UserRepository;
import com.aaxis.pojo.User;
import com.aaxis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public Iterable<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {

        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameIgnoreCase(String userName) {
        return this.userRepository.findByUsernameIgnoreCase(userName);
    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        return this.userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void save(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }
}
