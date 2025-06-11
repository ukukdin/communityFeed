package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatedPostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.ui.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updatePost(@PathVariable(name = "commentId") Long commentId,@RequestBody UpdatedPostRequestDto dto) {
        Post post = postService.updatePost(commentId,dto);
        return Response.ok(post.getId());
    }

    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto) {
        postService.likePost(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto) {
        postService.unlikePost(dto);
        return Response.ok(null);
    }
}
