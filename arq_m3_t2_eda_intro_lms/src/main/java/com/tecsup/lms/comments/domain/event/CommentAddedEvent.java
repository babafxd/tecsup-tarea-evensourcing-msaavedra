package com.tecsup.lms.comments.domain.event;

import com.tecsup.lms.shared.domain.event.DomainEvent;
import lombok.Getter;

@Getter
public class CommentAddedEvent extends DomainEvent {
    private final Long commentId;
    private final String studentId;
    private final String courseId;
    private final String comment;
    private final int rating;


    public CommentAddedEvent(Long commentId, String studentId, String courseId, String comment, int rating) {
        super();
        this.commentId = commentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.comment = comment;
        this.rating = rating;
    }

}
