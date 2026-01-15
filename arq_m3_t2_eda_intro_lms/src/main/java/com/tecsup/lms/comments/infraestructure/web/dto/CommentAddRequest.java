package com.tecsup.lms.comments.infraestructure.web.dto;

import lombok.Data;

@Data
public class CommentAddRequest {
    private String studentId;
    private String courseId;
    private String comment;
    private int rating;
}
