package com.nnamdi.noteapp.services.impl;

import com.nnamdi.noteapp.domain.request.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.dto.NotesDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.exceptions.ModelAlreadyExistException;
import com.nnamdi.noteapp.exceptions.ModelNotFoundException;
import com.nnamdi.noteapp.model.Notes;
import com.nnamdi.noteapp.repositories.NotesRepository;
import com.nnamdi.noteapp.services.NotesService;
import com.nnamdi.noteapp.utils.AppUtil;
import com.nnamdi.noteapp.utils.NotesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
        log.info("about to update note by id {}", noteId);
        Notes noteDetails = findNoteById(noteId);
        final var updateNote = notesUtil.updateNoteEntity(noteDetails,updateRequestDto);
        repository.save(updateNote);

        log.info("note updated successfully, {}", noteDetails.getId());
        return  modelMapper.map(updateNote, NotesDto.class);

    }

    @Override
    public NotesDto getNote(String noteId) {
        log.info("about to find notes by id {}", noteId);
        Notes note = findNoteById(noteId);
        return  modelMapper.map(note, NotesDto.class);

    }

    @Override
    public Notes findNoteById(String noteId) {
        Optional<Notes> notes = repository.findById(noteId);
        if (notes.isEmpty()) {
            throw  new ModelNotFoundException("Note not found");
        }
        return modelMapper.map(notes, Notes.class);

    }

    @Override
    public Page<NotesDto> getNotes(int page, int limit, String searchValue) {
        log.info("about to retrieve all notes by pagination {}, {}", page, limit);
        AppUtil.validatePageRequest(page, limit);
        Pageable pageable = PageRequest.of(page -1, limit);
        if (searchValue.isEmpty()){
            return notesDtos(pageable);
        }
        return notesDtos(searchValue,pageable);
    }

    @Override
    public Notes deleteNote(String noteId) {
        Notes notes = findNoteById(noteId);
        repository.deleteById(noteId);
        return notes;
    }

    @Override
    public Page<NotesDto> getNotesByAuthor(String author, Pageable pageable) {
        log.info("about to retrieve all notes by pagination {}, {}", pageable.getPageNumber(), pageable.getPageSize());
        List<NotesDto> notesDtos;
        Page<Notes> notes = repository.findByAuthor(author, pageable);
        notesDtos = notes.getContent().stream()
                .map(note -> modelMapper.map(note, NotesDto.class)).toList();
        return new PageImpl<>(notesDtos, notes.getPageable(), notes.getTotalElements());
    }

    private Page<NotesDto> notesDtos(Pageable pageable) {
        Page<Notes> notes = repository.findAll(pageable);
        List<NotesDto> notesDtoList = notes.getContent().stream()
                .map(note -> modelMapper.map(note, NotesDto.class)).toList();
        return new PageImpl<>(notesDtoList, notes.getPageable(), notes.getTotalElements());
    }

    private Page<NotesDto> notesDtos(String searchValue, Pageable pageable) {
        return getNotesByAuthor(searchValue, pageable);
    }
}
