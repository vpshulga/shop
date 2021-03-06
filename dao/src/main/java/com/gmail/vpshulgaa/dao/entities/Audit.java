package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_AUDIT")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "F_EVENT_TYPE", length = 30)
    private String eventType;
    @Column(name = "F_CREATED")
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        Audit audit = (Audit) o;
        return Objects.equals(id, audit.id) &&
                Objects.equals(eventType, audit.eventType) &&
                Objects.equals(created, audit.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventType, created);
    }
}
