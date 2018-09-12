package com.gmail.vpshulgaa.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_AUDIT")
public class Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_EVENT_TYPE", length = 30)
    private String eventType;
    @Column(name = "F_CREATED")
    private LocalDateTime created;
}
