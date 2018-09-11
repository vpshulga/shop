package com.gmail.vpshulgaa.dao.students.dao.impl;

import com.gmail.vpshulgaa.dao.students.dao.RecordBookDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.RecordBook;

public class RecordBookDaoImpl extends StudentsGenericDaoImpl<RecordBook> implements RecordBookDao {

    public RecordBookDaoImpl(Class<RecordBook> clazz) {
        super(clazz);
    }
}
