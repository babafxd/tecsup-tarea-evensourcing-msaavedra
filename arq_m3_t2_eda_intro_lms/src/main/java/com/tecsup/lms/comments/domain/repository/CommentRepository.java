package com.tecsup.lms.comments.domain.repository;

import com.tecsup.lms.comments.domain.model.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    Optional<Comment> findById(Long id);

}
