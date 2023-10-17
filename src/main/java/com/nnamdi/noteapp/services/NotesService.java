package com.nnamdi.noteapp.services;

import com.nnamdi.noteapp.domain.request.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.dto.NotesDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.model.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotesService {
    NotesDto createNote(NotesRequestDto requestDto);
    NotesDto updateNote(String noteId, NoteUpdateRequestDto updateRequestDto);

    NotesDto getNote(String noteId);

    Notes findNoteById(String noteId);

    Page<NotesDto> getNotes(int page, int limit, String searchValue);

    Notes deleteNote(String noteId);

    Page<NotesDto> getNotesByAuthor(String author, Pageable pageable);


}
