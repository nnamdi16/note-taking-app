package com.nnamdi.noteapp.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotesRequestDto implements Serializable {
    @NotBlank(message = "title of the note must be provided")
    private String title;

    @NotBlank(message = "content of the note must be provided")
    private String content;

    @NotBlank(message = "author of the note must be provided")
    private String author;
}
