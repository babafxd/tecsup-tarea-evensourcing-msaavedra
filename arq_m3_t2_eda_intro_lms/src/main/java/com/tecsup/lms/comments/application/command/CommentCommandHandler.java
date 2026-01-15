package com.tecsup.lms.comments.application.command;

import com.tecsup.lms.comments.domain.event.CommentAddedEvent;
import com.tecsup.lms.comments.domain.event.CommentEditedEvent;
import com.tecsup.lms.comments.domain.model.Comment;
import com.tecsup.lms.comments.domain.repository.CommentRepository;
import com.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CommentCommandHandler {

    private final MemoryEventStore eventStore;
    private final CommentRepository repository;

    public Comment save(CommentCommand command) {

        Comment comment = Comment.builder()
                .studentId(command.getStudentId())
                .courseId(command.getCourseId())
                .comment(command.getComment())
                .rating(command.getRating())
                .createdAt(LocalDateTime.now())
                .build();

        Comment saved = repository.save(comment);

        CommentAddedEvent event = new CommentAddedEvent(
                saved.getCommentId(),
                command.getStudentId(),
                command.getCourseId(),
                command.getComment(),
                command.getRating()
        );

        eventStore.save(saved.getCommentId().toString(), event);

        return saved;
    }

    public Comment update(CommentCommand command) {

        Comment comment = repository.findById(command.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + command.getCommentId()));

        comment.setComment(command.getComment());
        comment.setRating(command.getRating());

        Comment updated = repository.save(comment);

        CommentEditedEvent event = new CommentEditedEvent(
                command.getCommentId(),
                updated.getStudentId(),
                updated.getCourseId(),
                command.getComment(),
                command.getRating()
        );

        eventStore.save(updated.getCommentId().toString(), event);

        return updated;

    }

}
