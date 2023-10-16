package com.nnamdi.noteapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notes")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notes extends AbstractBaseEntity implements Serializable {
    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "content", length = 5000)
    private String content;

}
