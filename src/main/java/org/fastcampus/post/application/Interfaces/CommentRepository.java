package org.fastcampus.post.application.Interfaces;

import org.fastcampus.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);

}
