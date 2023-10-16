package com.nnamdi.noteapp.model;


import com.nnamdi.noteapp.utils.AppUtil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Slf4j
@Data
@MappedSuperclass
public class AbstractBaseEntity implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private String id;

    @LastModifiedDate
    @Column(name = "updated_date")
    private ZonedDateTime lastModifiedDate;

    @LastModifiedBy
    @Column(name = "updated_by", length = 40)
    private String lastModifiedBy;

    @CreatedDate
    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 40)
    private String createdBy;

    @PrePersist
    public void  abstractPrePersist() {
        log.debug("about to run abstractPrePersist method");
        createdDate = ZonedDateTime.now();
        lastModifiedDate = ZonedDateTime.now();
        if (AppUtil.stringIsNullOrEmpty(id)) {
            id = AppUtil.generateUUIDString();
        }
        log.debug("finished running abstractPrePersist method ");
    }

    @PreUpdate
    public void abstractPreUpdate() {
        lastModifiedDate = ZonedDateTime.now();
    }



}
