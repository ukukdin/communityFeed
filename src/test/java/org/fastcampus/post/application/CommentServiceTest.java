package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate {


    private final CommentService commentService = FakeObjectFactory.getCommentService();
    private final String commentContent = "this is test comment";

    CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContent);

    @Test
    void givenRequestDto_whenCreate_thenReturnPost() {
        //when
        Comment comment = commentService.createComment(dto);

        //then
        String content = comment.getContent();
        assertEquals(commentContent, content);
    }

    @Test
    void givenRequestDto_whenUpdate_thenReturnPost() {
        // given
        Comment comment = commentService.createComment(dto);

        // when
        String updatedCommentContent = "this is updated comment";
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(
                user.getId(), updatedCommentContent);
        Comment updatedComment = commentService.updateComment(comment.getId(), updateCommentRequestDto);

        // then
        Content content = updatedComment.getContentObject();
        assertEquals(updatedCommentContent, content.getContentText());
    }

    @Test
    void givenRequestDto_whenLikeComment_thenReturnCommentWithLike(){
        Comment comment = commentService.createComment(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(otherUser.getId(), comment.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }


    @Test
    void givenRequestDto_whenUnLikeComment_thenReturnCommentWithUnLike(){
        Comment comment = commentService.createComment(dto);

        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), user.getId());
        commentService.likeComment(likeRequestDto);
        commentService.UnlikeComment(likeRequestDto);

        assertEquals(0, comment.getLikeCount());
    }
}
