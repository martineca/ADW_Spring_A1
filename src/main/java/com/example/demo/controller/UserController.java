package com.example.demo.controller;

import com.example.demo.domain.LoginForm;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public String loginView(Model model)
    {
        LoginForm user = new LoginForm();
        model.addAttribute("user", user);
        return "user/login";
    }


    @RequestMapping(value="/login", method = RequestMethod.POST)
    // @ResponseBody
    public String login(Model model, @ModelAttribute("user") LoginForm user, HttpSession session){

        if(userService.validateLogin(user)==null || userService.validateLogin(user).size() == 0){
            model.addAttribute("user", user);
            model.addAttribute("message", "Incorrect password");
            return "user/login";
        }
        session.setAttribute("login", true);
        return "redirect:/";
    }

    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session)
    {
        session.removeAttribute("login");
        return "redirect:/user/login";
    }

    @RequestMapping(value ="/register", method = RequestMethod.GET)
    public String registerView(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
   // @ResponseBody
    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("user", user);
            model.addAttribute("message","Please don't leave an empty field.");
            return "user/register";
        }
        userService.save(user);
       // return "Register successful for " + user.getFirstname() + " " + user.getLastname();
        return "redirect:/";
    }

    @RequestMapping(value="/update/{user}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable User user)
    {

        model.addAttribute("user", user);
        return "user/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    // @ResponseBody
    public String update(Model model, @ModelAttribute("user") User user){
        userService.save(user);
        // return "Register successful for " + user.getFirstname() + " " + user.getLastname();
        return "redirect:/";
    }


    @RequestMapping(value="/delete/{user}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable User user){
        userService.delete(user);

        return "User" + user.getFirstname() + " deleted";
    }
}
