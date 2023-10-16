package com.nnamdi.noteapp.utils;

import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.model.Notes;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotesUtil {
    private final ModelMapper modelMapper;

    public Notes buildNoteEntity (NotesRequestDto requestDto) {
        return modelMapper.map(requestDto, Notes.class);
    }
}
