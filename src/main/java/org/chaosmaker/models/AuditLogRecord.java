package org.chaosmaker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class AuditLogRecord {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;
    @NotNull
    protected String message;
    @NotNull
    protected Long entityId;
    @NotNull
    protected Class entityClass;
    @NotNull
    protected Long userId;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdOn = new Date();

    public AuditLogRecord() {
    }

    public AuditLogRecord(String message, Class entityClass, Long userId) {
        this.message = message;
        this.entityClass = entityClass;
        this.userId = userId;
    }

    public AuditLogRecord(Long id, String message, Long entityId, Class entityClass, Long userId, Date createdOn) {
        this.id = id;
        this.message = message;
        this.entityId = entityId;
        this.entityClass = entityClass;
        this.userId = userId;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
