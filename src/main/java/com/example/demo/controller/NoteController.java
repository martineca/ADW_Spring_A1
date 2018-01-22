package com.example.demo.controller;

import com.example.demo.domain.Note;
import com.example.demo.domain.NoteSearchFrom;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @RequestMapping(value ="/addNote", method = RequestMethod.GET)
    public String addNoteView(Model model)
    {
        Note note = new Note();
        model.addAttribute("note", note);
        return "note/addNote";
    }

    @RequestMapping(value ="/searchNote", method = RequestMethod.GET)
    public String searchNoteView(Model model)
    {
        NoteSearchFrom searchForm = new NoteSearchFrom();
        model.addAttribute("searchCriteria", searchForm);
        return "note/searchNote";
    }


    @RequestMapping(value="/searchNote", method = RequestMethod.POST)
    public String searchNote(Model model, @ModelAttribute("searchCriteria") NoteSearchFrom searchForm){
        List<Note> notes = noteService.searchNotes(searchForm);
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("notes",notes);
       // return "Register successful for " + user.getFirstname() + " " + user.getLastname();
        return "note/searchNote";
    }



    @RequestMapping(value="/addNote", method = RequestMethod.POST)
    // @ResponseBody
    public String addNote(Model model, @ModelAttribute("note") Note note){
        noteService.save(note);
        // return "Register successful for " + user.getFirstname() + " " + user.getLastname();
        return "redirect:/";
    }

    @RequestMapping(value="/updateNote/{note}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable Note note)
    {

        model.addAttribute("note", note);
        return "note/updateNote";
    }

    @RequestMapping(value="/updateNote", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("user") Note note){
        noteService.save(note);
        // return "Register successful for " + user.getFirstname() + " " + user.getLastname();
        return "redirect:/";
    }


    @RequestMapping(value="/deleteNote/{note}", method = RequestMethod.GET)
    //@ResponseBody
    public String delete(@PathVariable Note note){
        noteService.delete(note);

        return "note/noteDeleted";
    }
}
