package com.gmail.vpshulgaa.service.students.service;

import com.gmail.vpshulgaa.dao.students.dao.entities.University;
import java.util.List;

public interface UniversityService {
    University findOne(final Long id);

    List<University> findAll();

    void create(final University university);

    void update(final University university);

    void delete(final University university);

    void deleteById(final Long id);
}
