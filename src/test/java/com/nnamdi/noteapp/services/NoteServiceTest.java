package com.nnamdi.noteapp.services;

import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.services.impl.NotesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
class NoteServiceTest {
    @Mock
    private NotesService notesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notesService = new NotesServiceImpl();
    }

    @Test
    void testToCreateNote() {
        NotesRequestDto requestDto = buildNoteRequestDto();
        final  var note = notesService.createNote(requestDto);
        assertThat(note).isNotNull();
        assertThat(note.getTitle()).isNotNull();
        assertThat(note.getContent().length()).isLessThanOrEqualTo(5000);
    }

    NotesRequestDto buildNoteRequestDto() {
        return  NotesRequestDto.builder()
                .title("Health Index")
                .content("Health is wealth in all generation")
                .author("John Doe")
                .build();
    }
}
