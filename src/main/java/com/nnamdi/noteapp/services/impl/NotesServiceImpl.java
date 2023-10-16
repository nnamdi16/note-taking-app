package com.nnamdi.noteapp.services.impl;

import com.nnamdi.noteapp.domain.dto.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.dto.NotesDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.services.NotesService;
import org.springframework.data.domain.Page;

public class NotesServiceImpl implements NotesService {
    @Override
    public NotesDto createNote(NotesRequestDto requestDto) {
        return null;
    }

    @Override
    public NotesDto updateNote(String noteId, NoteUpdateRequestDto updateRequestDto) {
        return null;
    }

    @Override
    public NotesDto getNote(String noteId) {
        return null;
    }

    @Override
    public Page<NotesDto> getNotes(int page, int limit) {
        return null;
    }

    @Override
    public NotesDto deleteNote(String noteId) {
        return null;
    }

    @Override
    public Page<NotesDto> getNotesByAuthor(String author, int page, int limit) {
        return null;
    }
}
