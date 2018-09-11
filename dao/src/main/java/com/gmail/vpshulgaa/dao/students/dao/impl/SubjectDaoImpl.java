package com.gmail.vpshulgaa.dao.students.dao.impl;

import com.gmail.vpshulgaa.dao.students.dao.SubjectDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.Subject;

public class SubjectDaoImpl extends StudentsGenericDaoImpl<Subject> implements SubjectDao {
    public SubjectDaoImpl(Class<Subject> clazz) {
        super(clazz);
    }
}
