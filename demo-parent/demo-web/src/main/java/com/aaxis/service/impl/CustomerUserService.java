package com.aaxis.service.impl;

import com.aaxis.dao.UserRepository;
import com.aaxis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserService implements UserDetailsService
{

    @Autowired
    UserRepository userRepository;


    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
//        User user = userRepository.findByEmailIgnoreCase(email);

        if (user == null){
            throw  new UsernameNotFoundException("Not exist the user．");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        session.setAttribute("username",username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role:user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        System.out.println(user.getUsername()+authorities.toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

}
