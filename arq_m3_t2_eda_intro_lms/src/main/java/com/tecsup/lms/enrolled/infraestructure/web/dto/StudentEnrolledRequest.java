package com.tecsup.lms.enrolled.infraestructure.web.dto;

@lombok.Data
public class StudentEnrolledRequest {
    private  String studentName;
    private  String email;
    private  String courseId;
    private  String courseName;
}
