package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Comment;
import com.gmail.vpshulgaa.dao.entities.News;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.CommentDto;
import com.gmail.vpshulgaa.service.dto.NewsDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserConverter implements Converter<UserDto, User> {

    @Override
    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setRoleId(dto.getRoleId());
        ProfileConverter profileConverter = new ProfileConverter();
        if (dto.getProfileDto() != null) {
            Profile profile = profileConverter.toEntity(dto.getProfileDto());
            user.setProfile(profile);
            profile.setUser(user);
        }
        NewsConverter newsConverter = new NewsConverter();
        Set<News> news = new HashSet<>();
        for (NewsDto newsDto : dto.getNewsDtoSet()) {
            news.add(newsConverter.toEntity(newsDto));
        }
        user.setNews(news);
        CommentConverter commentConverter = new CommentConverter();
        Set<Comment> comments = new HashSet<>();
        for (CommentDto commentDto : dto.getCommentDtoSet()) {
            comments.add(commentConverter.toEntity(commentDto));
        }
        user.setComments(comments);
        return user;
    }


    @Override
    public List<User> toEntityList(List<UserDto> list) {
        return null;
    }
}
