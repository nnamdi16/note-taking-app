package com.nnamdi.noteapp.utils;

import com.nnamdi.noteapp.domain.request.NoteUpdateRequestDto;
import com.nnamdi.noteapp.domain.request.NotesRequestDto;
import com.nnamdi.noteapp.model.Notes;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotesUtil {
    private final ModelMapper modelMapper;

    public Notes buildNoteEntity (NotesRequestDto requestDto) {
        return modelMapper.map(requestDto, Notes.class);
    }

    public Notes updateNoteEntity (Notes existingNotes, NoteUpdateRequestDto requestDto) {
        if (StringUtils.isNotBlank(requestDto.getContent())) {
            existingNotes.setContent(requestDto.getContent());
        }
        if (StringUtils.isNotBlank(requestDto.getLastModifiedBy())) {
            existingNotes.setLastModifiedBy(requestDto.getLastModifiedBy());
        }
        return existingNotes;
    }
}
