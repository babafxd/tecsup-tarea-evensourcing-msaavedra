package com.tecsup.lms.enrolled.infraestructure.persistence.repository;

import com.tecsup.lms.enrolled.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentRepository extends JpaRepository<Student, Long> {
}
