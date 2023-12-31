package com.nnamdi.noteapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto implements Serializable {
    private String id;
    private String title;
    private String content;
    @JsonProperty("author")
    private String createdBy;


}
