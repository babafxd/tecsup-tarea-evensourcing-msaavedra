package com.tecsup.lms.comments.infraestructure.persistence.repository;

import com.tecsup.lms.comments.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
