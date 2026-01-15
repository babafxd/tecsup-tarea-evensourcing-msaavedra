package com.tecsup.lms.enrolled.infraestructure.persistence.adapter;

import com.tecsup.lms.enrolled.domain.model.Student;
import com.tecsup.lms.enrolled.domain.repository.StudentRepository;
import com.tecsup.lms.enrolled.infraestructure.persistence.repository.JpaStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepository {

    private final JpaStudentRepository jpaRepository;

    @Override
    public Student save(Student student) {
        return jpaRepository.save(student);
    }

}
