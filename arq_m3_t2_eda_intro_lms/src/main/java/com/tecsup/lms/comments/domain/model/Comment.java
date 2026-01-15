package com.tecsup.lms.comments.domain.model;

import com.tecsup.lms.comments.domain.event.CommentAddedEvent;
import com.tecsup.lms.comments.domain.event.CommentEditedEvent;
import com.tecsup.lms.shared.domain.event.DomainEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String studentId;
    private String courseId;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;

    public Comment() {
    }


    public static Comment fromEvents(List<DomainEvent> events) {

        Comment comment = new Comment();
        for (DomainEvent event : events) {
            comment.apply(event);
        }
        return comment;
    }


    private void apply(DomainEvent event) {

        if (event instanceof CommentAddedEvent e) {
            //
            this.studentId = e.getStudentId();
            this.courseId = e.getCourseId();
            this.comment = e.getComment();
            this.rating = e.getRating();

        } else if (event instanceof CommentEditedEvent e) {

            this.commentId = e.getCommentId();
            this.studentId = e.getStudentId();
            this.courseId = e.getCourseId();
            this.comment = e.getComment();
            this.rating = e.getRating();

        }

    }

}
