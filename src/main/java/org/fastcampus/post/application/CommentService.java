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
import org.springframework.stereotype.Service;

@Service
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
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto dto) {

        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        Comment comment = new Comment(null,post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto) {
        Comment comment = getComment(commentId);

        User user = userService.getUser(commentId);

        comment.updateContent(user,dto.content());
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
            comment.unlike();
            likeRepository.unlike(comment, user);

        }


    }
}
