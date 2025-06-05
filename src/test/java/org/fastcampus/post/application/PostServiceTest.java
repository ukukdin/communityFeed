package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostApplicationTestTemplate {


    @Test
    void givenRequestDto_whenCreate_thenReturnPost() {

        //when
        Post savePost = postService.createPost(dto);

        //then
        Post post = postService.getPost(savePost.getId());
        assertEquals(savePost, post);
    }
    @Test
    void givenCreatePost_WhenUpdate_thenReturnUpdatedPost() {
        //give
        Post savePost = postService.createPost(dto);

        //when
        Post post = postService.updatePost(savePost.getId(),dto);

        //then
        assertEquals(savePost.getId(), post.getId());
        assertEquals(savePost.getAuthor(), post.getAuthor());
        assertEquals(savePost.getContent(), post.getContent());


    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike(){
        //give
        Post savePost = postService.createPost(dto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        //then

        assertEquals(1, savePost.getLikeCount());
    }
    @Test
    void givenCreatedPost_whenLikedTwice_thenReturnPostWithLike(){
        //give
        Post savePost = postService.createPost(dto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        //then

        assertEquals(1, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenUnliked_thenReturnPostWithUnlike(){
        //given
        Post savePost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        postService.unlikePost(likeRequestDto);

        assertEquals(0, savePost.getLikeCount());

    }

    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithUnlike(){
        Post savePost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        assertEquals(0, savePost.getLikeCount());

    }


}
