package com.tecsup.lms.enrolled.domain.model;

import com.tecsup.lms.enrolled.domain.event.LessonCompletedEvent;
import com.tecsup.lms.enrolled.domain.event.StudentEnrolledEvent;
import com.tecsup.lms.shared.domain.event.DomainEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String courseId;
    private String courseName;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    private LocalDateTime createdAt;
    private int progressPercentage;

    public enum StudentStatus {
        CREATE
    }

    public Student() {
        this.progressPercentage = 0;
    }

    public static Student fromEvents(List<DomainEvent> events) {

        Student student = new Student();

        for (DomainEvent event : events) {
            student.apply(event);
        }

        return student;

    }

    /*
     *
     *
     * */
    private void apply(DomainEvent event) {

        if (event instanceof StudentEnrolledEvent e) {
            //
            this.id = Long.valueOf(e.getStudentId());
            this.name = e.getName();
            this.email = e.getEmail();
            this.courseId = e.getCourseId();
            this.courseName = e.getCourseName();
            this.progressPercentage = 0;

        } else if (event instanceof LessonCompletedEvent e) {

            this.progressPercentage = e.getNewProgressPercentage();

        }

    }
}
