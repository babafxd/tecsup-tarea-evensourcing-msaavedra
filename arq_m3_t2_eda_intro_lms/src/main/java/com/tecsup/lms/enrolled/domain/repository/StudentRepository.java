package com.tecsup.lms.enrolled.domain.repository;

import com.tecsup.lms.enrolled.domain.model.Student;

public interface StudentRepository {
    Student save(Student student);
}
