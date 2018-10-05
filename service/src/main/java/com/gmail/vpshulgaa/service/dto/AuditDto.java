package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuditDto {
    private long id;
    private String eventType;
    private LocalDateTime created;

    public AuditDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditDto auditDto = (AuditDto) o;
        return id == auditDto.id &&
                Objects.equals(eventType, auditDto.eventType) &&
                Objects.equals(created, auditDto.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventType, created);
    }
}
