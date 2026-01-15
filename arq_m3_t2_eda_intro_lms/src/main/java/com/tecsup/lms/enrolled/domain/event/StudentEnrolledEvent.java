package com.tecsup.lms.enrolled.domain.event;

import com.tecsup.lms.shared.domain.event.DomainEvent;
import lombok.Getter;

@Getter
public class StudentEnrolledEvent extends DomainEvent {

    private final String studentId;
    private final String name;
    private final String email;
    private final String courseId;
    private final String courseName;

    public StudentEnrolledEvent(String studentId, String name, String email, String courseId, String courseName) {
        super();
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.courseId = courseId;
        this.courseName = courseName;
    }

}
