package com.gmail.vpshulgaa.service.students.service;

import com.gmail.vpshulgaa.dao.students.dao.entities.Subject;
import java.util.List;

public interface SubjectService {
    Subject findOne(final Long id);

    List<Subject> findAll();

    void create(final Subject subject);

    void update(final Subject subject);

    void delete(final Subject subject);

    void deleteById(final Long id);
}
