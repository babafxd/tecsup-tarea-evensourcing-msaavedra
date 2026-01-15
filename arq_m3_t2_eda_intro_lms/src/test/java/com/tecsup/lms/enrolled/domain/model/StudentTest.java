package com.tecsup.lms.enrolled.domain.model;

import com.tecsup.lms.enrolled.domain.event.LessonCompletedEvent;
import com.tecsup.lms.enrolled.domain.event.StudentEnrolledEvent;
import com.tecsup.lms.shared.domain.event.DomainEvent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testStudentEnrollmentCreation() {

        StudentEnrolledEvent event = new StudentEnrolledEvent(
                "1",
                "MARCO SAAVEDRA",
                "XD@GMAIL.COM",
                "9999",
                "GEOMETRIA"
        );

        List<DomainEvent> events = List.of(event);

        Student student = Student.fromEvents(events);

        assertEquals(1, student.getId());
        assertEquals("MARCO SAAVEDRA", student.getName());
        assertEquals("XD@GMAIL.COM", student.getEmail());
        assertEquals("9999", student.getCourseId());
        assertEquals("GEOMETRIA", student.getCourseName());
        assertEquals(0, student.getProgressPercentage());


    }


    @Test
    void testEnrollmentProgressUpdate() {
        StudentEnrolledEvent event1 = new StudentEnrolledEvent(
                "88",
                "MARCO SAAVEDRA",
                "XD@GMAIL.COM",
                "9999",
                "GEOMETRIA"
        );

        LessonCompletedEvent event2 = new LessonCompletedEvent(
                "88",
                "lesson-01",
                25
        );

        LessonCompletedEvent event3 = new LessonCompletedEvent(
                "88",
                "lesson-02",
                50
        );

        List<DomainEvent> events = List.of(event1, event2, event3);

        Student student = Student.fromEvents(events);

        assertEquals(88, student.getId());
        assertEquals("MARCO SAAVEDRA", student.getName());
        assertEquals("XD@GMAIL.COM", student.getEmail());
        assertEquals("9999", student.getCourseId());
        assertEquals("GEOMETRIA", student.getCourseName());
        assertEquals(50, student.getProgressPercentage());



    }

}