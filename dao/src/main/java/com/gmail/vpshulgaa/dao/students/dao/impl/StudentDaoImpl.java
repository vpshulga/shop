package com.gmail.vpshulgaa.dao.students.dao.impl;

import com.gmail.vpshulgaa.dao.students.dao.StudentDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.Student;

public class StudentDaoImpl extends StudentsGenericDaoImpl<Student> implements StudentDao{

    public StudentDaoImpl(Class<Student> clazz) {
        super(clazz);
    }
}
