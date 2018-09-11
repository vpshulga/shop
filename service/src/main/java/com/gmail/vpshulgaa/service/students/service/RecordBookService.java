package com.gmail.vpshulgaa.service.students.service;

import com.gmail.vpshulgaa.dao.students.dao.entities.RecordBook;
import java.util.List;

public interface RecordBookService {
    RecordBook findOne(final Long id);

    List<RecordBook> findAll();

    void create(final RecordBook recordBook);

    void update(final RecordBook recordBook);

    void delete(final RecordBook recordBook);

    void deleteById(final Long id);
}
