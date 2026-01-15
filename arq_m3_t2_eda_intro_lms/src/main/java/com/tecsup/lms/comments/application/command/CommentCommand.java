package com.tecsup.lms.comments.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCommand {

    private final Long commentId;
    private final String studentId;
    private final String courseId;
    private final String comment;
    private final int rating;


    public static CommentCommand forCreate(String studentId, String courseId, String comment, int rating) {
        return new CommentCommand(null, studentId, courseId, comment, rating);
    }


    public static CommentCommand forUpdate(Long commentId, String comment, int rating) {
        return new CommentCommand(commentId, null, null, comment, rating);
    }


}
