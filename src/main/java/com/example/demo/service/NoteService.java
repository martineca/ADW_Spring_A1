package com.example.demo.service;

import com.example.demo.domain.Note;
import com.example.demo.domain.NoteRepository;

import com.example.demo.domain.NoteSearchFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class NoteService {
        @Autowired
        NoteRepository noteRepository;

        public Note save(Note u)
        {
            return noteRepository.save(u);
        }

        public List<Note> findAll()
        {
            return noteRepository.findAll();
        }

        public void delete(Note u){
            noteRepository.delete(u);
        }

        public List<Note> searchNotes(NoteSearchFrom note)
        {

            return noteRepository.searchNotes(note.getTitle());
        }


    }
