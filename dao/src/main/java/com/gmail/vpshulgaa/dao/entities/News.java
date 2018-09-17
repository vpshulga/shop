package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "F_USER_ID")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(title, news.title) &&
                Objects.equals(content, news.content) &&
                Objects.equals(created, news.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, created);
    }
}
