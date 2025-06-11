package org.fastcampus.post.ui;

import lombok.AllArgsConstructor;
import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.ui.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name="commentId") Long commentId, @RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId,dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
        commentService.UnlikeComment(dto);
        return Response.ok(null);
    }


}
