package com.tecsup.lms.enrolled.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentEnrolledCommand {

    private final String studentName;
    private final String email;
    private final String courseId;
    private final String courseName;

}
