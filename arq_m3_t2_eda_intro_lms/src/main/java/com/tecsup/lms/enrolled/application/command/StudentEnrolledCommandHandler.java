package com.tecsup.lms.enrolled.application.command;

import com.tecsup.lms.enrolled.domain.event.StudentEnrolledEvent;
import com.tecsup.lms.enrolled.domain.model.Student;
import com.tecsup.lms.enrolled.domain.repository.StudentRepository;
import com.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class StudentEnrolledCommandHandler {

    private final MemoryEventStore eventStore;
    private final StudentRepository repository;

    public Long enrollStudent(StudentEnrolledCommand command) {

        /*// Generea el ID de inscripción
        Long studentId = System.currentTimeMillis(); // Simulación de ID de inscripción*/

        Student student = Student.builder()
                .name(command.getStudentName())
                .email(command.getEmail())
                .courseId(command.getCourseId())
                .courseName(command.getCourseName())
                .status(Student.StudentStatus.CREATE)
                .createdAt(LocalDateTime.now())
                .build();

        Student saved = repository.save(student);

        // Crear evento de inscripción
        StudentEnrolledEvent event = new StudentEnrolledEvent(
                saved.getId().toString(),
                command.getStudentName(),
                command.getEmail(),
                command.getCourseId(),
                command.getCourseName()
        );

        // Almacenar el evento en el Event Store
        eventStore.save(saved.getId().toString(), event);

        return saved.getId();
    }

}
