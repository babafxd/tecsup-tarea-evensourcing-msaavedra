package com.tecsup.lms.enrolled.application.command;

import com.tecsup.lms.enrolled.domain.model.Student;
import com.tecsup.lms.enrolled.domain.repository.StudentRepository;
import com.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
class StudentEnrolledCommandHandlerTest {

    private StudentEnrolledCommandHandler handler;
    private MemoryEventStore eventStore;
    private ApplicationEventPublisher publisher;
    private StudentRepository repository;

    @BeforeEach
    void init() {
        publisher = mock(ApplicationEventPublisher.class);
        eventStore = new MemoryEventStore(publisher);
        repository = mock(StudentRepository.class);
        handler = new StudentEnrolledCommandHandler(eventStore, repository);
    }

    @Test
    void enrollStudent() {
        StudentEnrolledCommand command
                = new StudentEnrolledCommand(
                "500",
                "Katherine",
                "kramos@xd.com",
                "COURSE-001");

        Student studentConId = Student.builder()
                .id(1L) //
                .name(command.getStudentName())
                .email(command.getEmail())
                .build();

        when(repository.save(any(Student.class))).thenReturn(studentConId);

        Long enrollmentId = handler.enrollStudent(command);

        log.info("Student Enrollment ID: {}", enrollmentId);
        assertEquals(1L, enrollmentId);
        assertNotNull(enrollmentId);

        // Verificar que el evento se haya almacenado
        var events = eventStore.getEvents(enrollmentId.toString());
        assertEquals(1, events.size());


    }

}