package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {

    private final UserInfo userInfo = new UserInfo("name", "email");
    private final User user = new User(1L,userInfo );
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user,"content");
    @Test
    void givenPostCreateWhenLikeThenLikeCountShouldBe1(){
        //when
        post.like(otherUser);

        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeByOtherUser_thenThrowException() {
        //when, then
        assertThrows(IllegalArgumentException.class, ()-> post.like(user));
    }

    @Test
    void givenPostCreated_whenUnLike_thenLikeCountShouldBe0() {
        //given
        post.like(otherUser);

        //when
        post.unlike();

        //then
        assertEquals(0, post.getLikeCount());

    }

    @Test
    void givenPostCreated_whenUnLikeByOtherUser_thenThrowException() {
        //when
        post.unlike();

        //then
        assertEquals(0, post.getLikeCount());

    }

//    @Test
//    void givenPostUpdated_WhenUpdateContent_ThenLikeCountShouldBeUpdated(){
//        //given
//        String newPostContent = "new content";
//
//        //when
//        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);
//
//        assertEquals(newPostContent, post.getContent());
//
//    }

    @Test
    void givenPostCreated_whenUpdateOtherUserContent_thenThrowException() {

        //given
        String newPostContent = " new content";

        //when then
        assertThrows(IllegalArgumentException.class, ()-> post.updatePost(otherUser,newPostContent, PostPublicationState.PUBLIC));

    }

}
