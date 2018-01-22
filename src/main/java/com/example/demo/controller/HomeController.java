package com.example.demo.controller;


import com.example.demo.domain.Note;
import com.example.demo.service.NoteService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    NoteService noteService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session)
    {
        if(session.getAttribute("login") == null){
           return "redirect:/user/login";
        }
        List<Note> notes = noteService.findAll();
        model.addAttribute("notes", notes);
        return "index";
    }

}
