package com.aaxis.controller;

import com.aaxis.pojo.User;
import com.aaxis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        userService.save(user);
        return "redirect:user/userInfo";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(Model model) {
//        User user = userService.findByEmailIgnoreCase(email);
        User user = userService.findByUsername((String) request.getSession().getAttribute("username"));
        model.addAttribute("user", user);
        return "user/userInfo";
    }


    @RequestMapping(value = "/userInfoEdit",method = RequestMethod.GET)
    public String editUser(@RequestParam String  username, Model model) {
        model.addAttribute("user",userService.findByUsername(username));
        return "user/userInfoEdit";
    }


    @RequestMapping(value = "/userInfoEdit")
    public String editUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult) {
        User u = userService.getOne(user.getUserId());
        if (user.getUsername() != null){
            u.setUsername(user.getUsername());
        }
        if(user.getPassword()!= null){
            u.setPassword(user.getPassword());
        }
        if(user.getEmail()!= null){
            u.setPassword(user.getEmail());
        }

        userService.save(u);
        return "user/userInfo";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model){
        model.addAttribute("users",userService.findAll());
        return "user/findAll";
    }


    @RequestMapping("/delete")
    public String delete(@RequestParam Long userId, Model model){
         userService.deleteById(userId);

        return "redirect:findAll";
    }
}
