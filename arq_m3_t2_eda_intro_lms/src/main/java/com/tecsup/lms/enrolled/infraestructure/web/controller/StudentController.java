package com.tecsup.lms.enrolled.infraestructure.web.controller;

import com.tecsup.lms.enrolled.application.StudentEnrolledUseCase;
import com.tecsup.lms.enrolled.application.command.StudentEnrolledCommand;
import com.tecsup.lms.enrolled.application.command.StudentEnrolledCommandHandler;
import com.tecsup.lms.enrolled.domain.model.Student;
import com.tecsup.lms.enrolled.infraestructure.web.dto.StudentEnrolledRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentEnrolledUseCase studentEnrolledUseCase;
    private final StudentEnrolledCommandHandler studentEnrolledCommandHandler;

    @PostMapping
    public ResponseEntity<Student> enrolled(@RequestBody StudentEnrolledRequest request) {
        Student student = studentEnrolledUseCase.studentEnrolled(
                request.getStudentName(),
                request.getEmail(),
                request.getCourseId(),
                request.getCourseName()
        );
        return ResponseEntity.ok(student);
    }

    @PostMapping
    @RequestMapping("/studentCommand")
    public ResponseEntity<Long> enrolledCommand(@RequestBody StudentEnrolledCommand request) {
        Long student = studentEnrolledCommandHandler.enrollStudent(request);
        return ResponseEntity.ok(student);


    }

}
