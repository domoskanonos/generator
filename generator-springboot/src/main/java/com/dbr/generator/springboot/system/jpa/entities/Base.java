package com.dbr.generator.springboot.system.jpa.entities;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Audited
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Base {

    public enum OperationEnum {
        REMOVE, INSERT, UPDATE
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "OPERATION")
    private OperationEnum operation;

    @Column(name = "CREATED_BY", nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "MODIFIED", nullable = false)
    @LastModifiedDate
    private Date modified;

    @PrePersist
    public void onPrePersist() {
        operation = OperationEnum.INSERT;
    }

    @PreUpdate
    public void onPreUpdate() {
        operation = OperationEnum.UPDATE;
    }

    @PreRemove
    public void onPreRemove() {
        operation = OperationEnum.REMOVE;
    }

}
