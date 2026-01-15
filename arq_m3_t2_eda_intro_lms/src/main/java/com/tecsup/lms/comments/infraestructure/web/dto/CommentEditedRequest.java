package com.tecsup.lms.comments.infraestructure.web.dto;

import lombok.Data;

@Data
public class CommentEditedRequest {
    private String comment;
    private int rating;
}
