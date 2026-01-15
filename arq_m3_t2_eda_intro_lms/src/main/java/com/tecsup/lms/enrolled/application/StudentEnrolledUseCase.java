package com.tecsup.lms.enrolled.application;

import com.tecsup.lms.enrolled.domain.event.StudentEnrolledEvent;
import com.tecsup.lms.enrolled.domain.model.Student;
import com.tecsup.lms.enrolled.domain.repository.StudentRepository;
import com.tecsup.lms.shared.domain.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class StudentEnrolledUseCase {

    private final StudentRepository repository;
    private final EventPublisher eventPublisher;

    public Student studentEnrolled(String name, String email, String courseId, String courseName) {

        Student student = Student.builder()
                .name(name)
                .email(email)
                .courseId(courseId)
                .courseName(courseName)
                .status(Student.StudentStatus.CREATE)
                .createdAt(LocalDateTime.now())
                .build();

        Student saved = repository.save(student);
        //log.info("Student enrolled: {}", saved.getId());

        // Publicar el evento
        eventPublisher.publish(
                new StudentEnrolledEvent(
                        saved.getId().toString(),
                        saved.getName(),
                        saved.getEmail(),
                        saved.getCourseId(),
                        saved.getCourseName()
                )
        );

        return saved;
    }

}
