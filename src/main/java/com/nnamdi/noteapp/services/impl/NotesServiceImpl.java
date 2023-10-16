package com.nnamdi.noteapp.services.impl;

import com.nnamdi.noteapp.domain.dto.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.dto.NotesDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.exceptions.ModelAlreadyExistException;
import com.nnamdi.noteapp.exceptions.ModelNotFoundException;
import com.nnamdi.noteapp.model.Notes;
import com.nnamdi.noteapp.repositories.NotesRepository;
import com.nnamdi.noteapp.services.NotesService;
import com.nnamdi.noteapp.utils.NotesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotesServiceImpl implements NotesService {

    private final ModelMapper modelMapper;
    private final NotesRepository repository;
    private final NotesUtil notesUtil;

    @Override
    public NotesDto createNote(NotesRequestDto requestDto) {
        log.info("about to create a new note {}", requestDto.getTitle());
        Optional<Notes> existingNote = repository.findByTitle(requestDto.getTitle());
        if (existingNote.isPresent()) {
            throw new ModelAlreadyExistException("Note already exist");
        }
        Notes note = notesUtil.buildNoteEntity(requestDto);
        final  var savedNote = repository.save(note);
        log.info("note created successfully {}", savedNote.getId());
        return modelMapper.map(savedNote, NotesDto.class);
    }

    @Override
    public NotesDto updateNote(String noteId, NoteUpdateRequestDto updateRequestDto) {
        return null;
    }

    @Override
    public NotesDto getNote(String noteId) {
        log.info("about to find notes by id {}", noteId);
        Notes  note =  repository.findById(noteId).orElseThrow(() -> new ModelNotFoundException("Note not found"));
        return  modelMapper.map(note, NotesDto.class);

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
