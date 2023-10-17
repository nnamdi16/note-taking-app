package com.nnamdi.noteapp.services;

import com.nnamdi.noteapp.domain.request.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.exceptions.ModelAlreadyExistException;
import com.nnamdi.noteapp.exceptions.ModelNotFoundException;
import com.nnamdi.noteapp.model.Notes;
import com.nnamdi.noteapp.repositories.NotesRepository;
import com.nnamdi.noteapp.services.impl.NotesServiceImpl;
import com.nnamdi.noteapp.utils.AppUtil;
import com.nnamdi.noteapp.utils.NotesUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class NoteServiceTest {
    @Mock
    private NotesService notesService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private NotesRepository repository;

    @Mock
    private NotesUtil notesUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        notesService = new NotesServiceImpl(modelMapper, repository, notesUtil );
    }

    @Test
    void testToCreateNote() {
        NotesRequestDto requestDto = buildNoteRequestDto();
        Notes note = buildNote();
        when(repository.findByTitle(anyString())).thenReturn(Optional.empty());
        when(notesUtil.buildNoteEntity(requestDto)).thenReturn(note);

        when(repository.save(any(Notes.class))).thenReturn(note);
        final  var response = notesService.createNote(requestDto);
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isNotNull();
        assertThat(response.getContent().length()).isLessThanOrEqualTo(5000);
    }

    @Test
    void testIfNoteAlreadyExist() {
        NotesRequestDto requestDto = buildNoteRequestDto();
        Notes note = buildNote();
        when(repository.findByTitle(anyString())).thenReturn(Optional.ofNullable(note));

        assertThatThrownBy(() -> notesService.createNote(requestDto)).hasMessage("Note already exist").isInstanceOf(ModelAlreadyExistException.class);
    }

    @Test
    void testToGetNote() {
        Notes note = buildNote();
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(note));
        final  var response = notesService.getNote(anyString());
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isNotBlank();
        assertThat(response.getId()).isNotBlank();
    }

    @Test
    void testGetNoteThrowsException() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> notesService.getNote(anyString())).hasMessage("Note not found").isInstanceOf(ModelNotFoundException.class);
    }

    @Test
    void testToUpdateNote() {
        Notes note = buildNote();
        when(repository.findById(anyString())).thenReturn(Optional.of(note));
        when(notesUtil.updateNoteEntity(note, updateRequestDto())).thenReturn(updatedNote());
        when(repository.save(any(Notes.class))).thenReturn(updatedNote());
        final  var response = notesService.updateNote(updatedNote().getId(), updateRequestDto());
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isNotBlank();
        assertThat(response.getId()).isNotBlank();
    }

    @Test
    void testToDeleteNote() {

        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(buildNote()));
         final var response = notesService.deleteNote(buildNote().getId());
         assertThat(response).isNotNull();

    }


    NotesRequestDto buildNoteRequestDto() {
        return  NotesRequestDto.builder()
                .title("Health Index")
                .content("Health is wealth in all generation")
                .createdBy("John Doe")
                .build();
    }

    NoteUpdateRequestDto updateRequestDto() {
        return NoteUpdateRequestDto.builder()
                .lastModifiedBy("John Doe")
                .content("Current health situations are improving")
                .build();
    }

    Notes updatedNote() {
        Notes note = buildNote();
        note.setContent(updateRequestDto().getContent());
        note.setLastModifiedBy(updateRequestDto().getLastModifiedBy());
        note.setLastModifiedDate(ZonedDateTime.now());
        return  note;
    }

    Notes buildNote() {
        NotesRequestDto notesRequestDto = buildNoteRequestDto();
        Notes note = Notes.builder()
                .title(buildNoteRequestDto().getTitle())
                .content(buildNoteRequestDto().getContent())
                .build();
        note.setCreatedBy(notesRequestDto.getCreatedBy());
        note.setCreatedDate(ZonedDateTime.now());
        note.setLastModifiedBy(notesRequestDto.getCreatedBy());
        note.setLastModifiedDate(ZonedDateTime.now());
        note.setId(AppUtil.generateUUIDString());
        return  note;
    }


}
