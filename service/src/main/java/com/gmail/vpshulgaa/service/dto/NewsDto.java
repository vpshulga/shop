package com.gmail.vpshulgaa.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NewsDto {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDto newsDto = (NewsDto) o;

        return id == newsDto.id && (title != null ? title.equals(newsDto.title) : newsDto.title == null)
                && (content != null ? content.equals(newsDto.content) : newsDto.content == null)
                && (created != null ? created.equals(newsDto.created) : newsDto.created == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}