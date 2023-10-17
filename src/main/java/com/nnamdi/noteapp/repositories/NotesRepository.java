package com.nnamdi.noteapp.repositories;

import com.nnamdi.noteapp.model.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Notes,String> {
    Optional<Notes> findByTitle(String title);

    @Query("select n from Notes n where n.createdBy = %?1% order by n.createdDate desc")
    Page<Notes> findByAuthor(String author, Pageable pageable);
}
