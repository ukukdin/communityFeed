package org.fastcampus.post.application.dto;

import org.fastcampus.post.domain.content.PostPublicationState;

public record UpdatedPostRequestDto(Long postId,Long userId, String content, PostPublicationState state) {
}
