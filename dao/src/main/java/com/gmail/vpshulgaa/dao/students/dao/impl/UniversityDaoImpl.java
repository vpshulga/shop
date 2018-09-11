package com.gmail.vpshulgaa.dao.students.dao.impl;

import com.gmail.vpshulgaa.dao.students.dao.UniversityDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.University;

public class UniversityDaoImpl extends StudentsGenericDaoImpl<University> implements UniversityDao {
    public UniversityDaoImpl(Class<University> clazz) {
        super(clazz);
    }
}
