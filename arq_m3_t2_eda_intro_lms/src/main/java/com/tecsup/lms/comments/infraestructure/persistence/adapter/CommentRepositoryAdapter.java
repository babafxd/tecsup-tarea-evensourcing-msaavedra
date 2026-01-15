package com.tecsup.lms.comments.infraestructure.persistence.adapter;

import com.tecsup.lms.comments.domain.model.Comment;
import com.tecsup.lms.comments.domain.repository.CommentRepository;
import com.tecsup.lms.comments.infraestructure.persistence.repository.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentRepositoryAdapter implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;


    @Override
    public Comment save(Comment comment) {
        return jpaCommentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return jpaCommentRepository.findById(id);
    }
}
