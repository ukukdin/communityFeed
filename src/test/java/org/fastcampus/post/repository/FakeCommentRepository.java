package org.fastcampus.post.repository;

import org.fastcampus.post.application.Interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {
   private final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {

            store.put(comment.getId(), comment);
            return comment;
        }
        long id = store.size() +1;
        Comment newComment = new Comment(comment.getAuthor(), id, comment.getPost(), comment.getContentObject());
        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
