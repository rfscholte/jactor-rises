package com.github.jactor.rises.client.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class NewPersistentDto implements Serializable {
    private Long id;
    private String createdBy;
    private LocalDateTime creationTime;
    private String updatedBy;
    private LocalDateTime updatedTime;

    NewPersistentDto() {
        createdBy = "todo: #156";
        creationTime = LocalDateTime.now();
        updatedBy = "todo: #156";
        updatedTime = LocalDateTime.now();
    }

    NewPersistentDto(NewPersistentDto persistent) {
        createdBy = persistent.getCreatedBy();
        creationTime = persistent.getCreationTime();
        id = persistent.getId();
        updatedBy = persistent.getUpdatedBy();
        updatedTime = persistent.getUpdatedTime();
    }

    public Long getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
