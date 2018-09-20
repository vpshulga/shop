package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuditDto {
    private long id;
    private String eventType;
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditDto auditDto = (AuditDto) o;

        return id == auditDto.id && (eventType != null ? eventType.equals(auditDto.eventType) : auditDto.eventType == null)
                && (created != null ? created.equals(auditDto.created) : auditDto.created == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
