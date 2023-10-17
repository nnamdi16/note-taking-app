package com.nnamdi.noteapp.controller;

import com.nnamdi.noteapp.domain.request.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.domain.response.APIResponseMessages;
import com.nnamdi.noteapp.domain.response.AppResponse;
import com.nnamdi.noteapp.services.NotesService;
import com.nnamdi.noteapp.utils.AppUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nnamdi.noteapp.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.noteapp.controller.BaseApiController.NOTES;


@RestController
@Slf4j
@Tag(name = "notes", description = "notes controller")
@RequestMapping(BASE_API_PATH  + NOTES)
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppResponse> createNote(@RequestBody @Valid NotesRequestDto requestDto) {
        AppResponse response = AppUtil.buildAppResponse(APIResponseMessages.SUCCESSFUL, true, notesService.createNote(requestDto),null, HttpStatus.OK.value());
        return  ResponseEntity.ok(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppResponse> getNotes(@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "limit", required = false, defaultValue = "50") int limit, @RequestParam(value = "author", required = false, defaultValue = "") String searchValue) {
        AppResponse response = AppUtil.buildAppResponse(APIResponseMessages.GET, true, notesService.getNotes(page, limit, searchValue),null, HttpStatus.OK.value());
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppResponse> getNote(@PathVariable("noteId") String noteId) {
        AppResponse response = AppUtil.buildAppResponse(APIResponseMessages.GET, true, notesService.getNote(noteId),null, HttpStatus.OK.value());
        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppResponse> deleteNote(@PathVariable("noteId") String noteId) {
        AppResponse response = AppUtil.buildAppResponse(APIResponseMessages.DELETED, true, notesService.deleteNote(noteId),null, HttpStatus.OK.value());
        return  ResponseEntity.ok(response);
    }

    @PutMapping("/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppResponse> updateNote(@PathVariable("noteId") String noteId, @RequestBody @Valid NoteUpdateRequestDto requestDto) {
        AppResponse response = AppUtil.buildAppResponse(APIResponseMessages.UPDATE, true, notesService.updateNote(noteId, requestDto),null, HttpStatus.OK.value());
        return  ResponseEntity.ok(response);
    }


}
