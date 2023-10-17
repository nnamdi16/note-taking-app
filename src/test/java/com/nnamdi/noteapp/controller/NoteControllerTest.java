package com.nnamdi.noteapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nnamdi.noteapp.domain.dto.NotesDto;
import com.nnamdi.noteapp.domain.request.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.model.Notes;
import com.nnamdi.noteapp.services.NotesService;
import com.nnamdi.noteapp.utils.AppUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


import static com.nnamdi.noteapp.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.noteapp.controller.BaseApiController.NOTES;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NotesController.class)
class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NotesService notesService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Gson gson;

    String noteRequest;

    String updateNoteRequest;
    private final String URL = BASE_API_PATH + NOTES ;

    @BeforeEach
    void setup() throws JsonProcessingException {
        noteRequest = objectMapper.writeValueAsString(buildNoteRequestDto());
        updateNoteRequest = objectMapper.writeValueAsString(buildNoteUpdate());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(notesService);
    }

    @Test
    void createNote() {
        when(notesService.createNote(any(NotesRequestDto.class))).thenReturn(notesDto(buildNote()));
        try {
            mockMvc.perform(
                            post(URL)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(noteRequest)
                    )
                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    @Test
    void getNotes() {
        List<NotesDto> notesList = new ArrayList<>();
        notesList.add(notesDto(buildNote()));
        Page<NotesDto> notesDtoPage = new PageImpl<>(notesList);

        when(notesService.getNotes(anyInt(), anyInt(), anyString())).thenReturn(notesDtoPage);
        try {
            mockMvc.perform(
                            get(URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .param("page", "0")
                                    .param("limit", "5")
                                    .param("author", "")
                    ).andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    @Test
    void getNote() {
        when(notesService.getNote( anyString())).thenReturn(notesDto(buildNote()));
        try {
            mockMvc.perform(
                            get(URL + "/" + buildNote().getId())
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }



    @Test
    void deleteNote() {
        when(notesService.deleteNote( anyString())).thenReturn(buildNote());
        try {
            mockMvc.perform(
                            delete(URL + "/" + buildNote().getId())
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    @Test
    void updateNote() {
        when(notesService.updateNote( anyString(), any(NoteUpdateRequestDto.class) )).thenReturn(notesDto(buildNote()));
        try {
            mockMvc.perform(
                            put(URL + "/" + buildNote().getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(updateNoteRequest)
                    )

                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    NotesRequestDto buildNoteRequestDto() {
          return NotesRequestDto.builder()
                .title("Health Index")
                .content("Health is wealth in all generation")
                .createdBy("John Doe")
                .build();
    }

    NoteUpdateRequestDto buildNoteUpdate() {
        return NoteUpdateRequestDto.builder()
                .content("French fries ist sehr lecker")
                .lastModifiedBy("Campbell Cam")
                .build();
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

    NotesDto notesDto(Notes notes) {
        return  NotesDto.builder()
                .id(notes.getId())
                .content(notes.getContent())
                .title(notes.getTitle())
                .createdBy(notes.getCreatedBy())
                .build();
    }
}
