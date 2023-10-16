package com.nnamdi.noteapp.repositories;

import com.nnamdi.noteapp.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Notes,String> {
}
