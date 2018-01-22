package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {

@Query("SELECT u FROM Note u WHERE u.title LIKE %?1%")
List<Note> searchNotes(String title);

}

