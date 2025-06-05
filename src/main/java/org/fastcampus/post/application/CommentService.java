package org.fastcampus.post.application;

import org.fastcampus.post.application.Interfaces.CommentRepository;
import org.fastcampus.post.application.Interfaces.LikeRepository;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class CommentService {
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService( UserService userService, PostService postService, CommentRepository commentRepository,LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(CreateCommentRequestDto dto) {

        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        Comment comment = Comment.createComment(user, post, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updatePost(user,dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)) {
            return;
        }
        comment.like(user);
        likeRepository.like(comment, user);

    }

    public void UnlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)) {
            comment.disLike();
            likeRepository.unlike(comment, user);

        }


    }
}
