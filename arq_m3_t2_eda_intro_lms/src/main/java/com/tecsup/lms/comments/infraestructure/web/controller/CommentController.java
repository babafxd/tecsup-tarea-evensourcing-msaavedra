package com.tecsup.lms.comments.infraestructure.web.controller;

import com.tecsup.lms.admin.infraestructure.web.dto.EDAResponse;
import com.tecsup.lms.comments.application.command.CommentCommand;
import com.tecsup.lms.comments.application.command.CommentCommandHandler;
import com.tecsup.lms.comments.domain.model.Comment;
import com.tecsup.lms.comments.infraestructure.web.dto.CommentAddRequest;
import com.tecsup.lms.comments.infraestructure.web.dto.CommentEditedRequest;
import com.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentCommandHandler commentCommandHandler;
    private final MemoryEventStore memoryEventStore;

    @PostMapping
    public ResponseEntity<Comment> save(@RequestBody CommentAddRequest request) {

        CommentCommand command = CommentCommand.forCreate(
                request.getStudentId(),
                request.getCourseId(),
                request.getComment(),
                request.getRating()
        );
        return ResponseEntity.ok(commentCommandHandler.save(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody CommentEditedRequest request) {

        CommentCommand command = CommentCommand.forUpdate(
                id,
                request.getComment(),
                request.getRating()
        );

        return ResponseEntity.ok(commentCommandHandler.update(command));
    }

}
