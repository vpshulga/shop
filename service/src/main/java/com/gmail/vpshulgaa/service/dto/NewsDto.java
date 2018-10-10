package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class NewsDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private String creator;
    private Long userId;

    public NewsDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDto newsDto = (NewsDto) o;
        return id == newsDto.id &&
                Objects.equals(title, newsDto.title) &&
                Objects.equals(content, newsDto.content) &&
                Objects.equals(created, newsDto.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, created);
    }
}
