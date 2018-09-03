package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_NEWS")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_TITLE", length = 100)
    private String title;
    @Column(name = "F_CONTENT", length = 1000)
    private String content;
    @Column(name = "F_CREATED")
    private LocalDateTime created;
    @Column(name = "F_USER_ID")
    private Long userId;
}
