package org.fastcampus.post.repository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.fastcampus.post.application.Interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        if(comment.getId() != null) {
            jpaCommentRepository.save(commentEntity);
            return comment;
        }
        commentEntity = jpaCommentRepository.save(commentEntity);
        return commentEntity.toComment();
    }

    @Override
    @Transactional
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow(null);
        return commentEntity.toComment();
    }
}
