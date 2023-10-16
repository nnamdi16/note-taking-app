package com.nnamdi.noteapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteUpdateRequestDto {
    @NotBlank(message = "content must be provided")
    @Size(max = 5000, message = "content must be less than 5000 characters")
    private String content;

    @NotBlank(message = " author of the update must be provided")
    @JsonProperty("author")
    private String lastModifiedBy;


}
