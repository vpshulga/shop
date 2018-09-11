package com.gmail.vpshulgaa.service.students.service;

import com.gmail.vpshulgaa.dao.students.dao.entities.Student;
import java.util.List;

public interface StudentService {
    Student findOne(final Long id);

    List<Student> findAll();

    void create(final Student student);

    void update(final Student student);

    void delete(final Student student);

    void deleteById(final Long id);
}
