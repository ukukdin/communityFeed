package org.fastcampus.post.comment;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {

    private final UserInfo userInfo = new UserInfo("name", "email");
    private final User user = new User(1L,userInfo );
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(user, 1L, post, new CommentContent("content"));

    @Test
    void givenCommentCreate_WhenLike_ThenLikeCountShouldBe1() {

        //given
        comment.like(otherUser);

        //then
        assertEquals(1,comment.getLikeCount());
    }

    @Test
    void givenCommentCreate_WhenLikeBySelf_ThenThrowException() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnlike_thenLikeCountShouldBe1() {
        //given
        comment.like(otherUser);

        //when
        comment.disLike();
        //then
        assertEquals(0,comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnlike_thenLikeCountShouldBe0() {
        //when
        comment.disLike();

        //then
        assertEquals(0,comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateContent_thenLShouldBeUpdated() {
        //given
        String newCommentContent = "new Content";
        //when
        comment.updatePost(user, newCommentContent);

        //then
        assertEquals(newCommentContent,comment.getContent());



    }
    @Test
    void givenComment_whenUpdateContentOver100_thenThrowException() {
        //given
        String newCommentContent = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updatePost(user, newCommentContent));
    }
}
