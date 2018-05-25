package com.aaxis.controller;

import com.aaxis.pojo.User;
import com.aaxis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {


    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JavaMailSender mailSender;

    private int num;

    @GetMapping("/login")
    public String login(){
        if(request.getSession().getAttribute("user") == null)
            return "login";
        else {
            return "redirect: index";
        }
    }
    @RequestMapping(value = {"/","/index"})
    public String index(Model model){
        model.addAttribute("user",request.getSession().getAttribute("user"));
        return "index";
    }

    @GetMapping("/register")
    public String addNewUser(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid User user, BindingResult bindingResult,@RequestParam String code, Model model) {

        if (bindingResult.hasErrors() && user !=null) {
            return "register";
        }
        if(userService.findByUsername(user.getUsername()) != null){
            bindingResult.addError(new FieldError("user","username","user name is exist"));
            return "register";
        }
        if (code == null) {
            model.addAttribute("errors", "code can't be null");

            return "register";
        } else if(Integer.valueOf(code) != num) {
            model.addAttribute("errors", "code error");
            return "register";
        }
        userService.save(user);

        return "redirect:results";
    }

    @RequestMapping("/sendEmail")
    public @ResponseBody String sendMail(HttpServletRequest request){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@wisely.net.cn");
        message.setTo(request.getParameter("email").toString());
        message.setSubject("主题：验证码");
        num =  (int)(Math.random()*900)+100;
        message.setText("Your number code is:" + num +
                ".");
        mailSender.send(message);
        logger.info("Code email has been send");
        return "success";
    }
}
