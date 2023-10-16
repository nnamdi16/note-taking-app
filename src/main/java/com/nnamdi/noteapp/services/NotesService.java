package com.nnamdi.noteapp.services;

import com.nnamdi.noteapp.domain.dto.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.dto.NotesDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import org.springframework.data.domain.Page;

public interface NotesService {
    NotesDto createNote(NotesRequestDto requestDto);
    NotesDto updateNote(String noteId, NoteUpdateRequestDto updateRequestDto);

    NotesDto getNote(String noteId);

    Page<NotesDto> getNotes(int page, int limit);

    NotesDto deleteNote(String noteId);

    Page<NotesDto> getNotesByAuthor(String author, int page, int limit);


}
