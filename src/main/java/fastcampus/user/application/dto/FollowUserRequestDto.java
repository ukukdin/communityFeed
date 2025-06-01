package fastcampus.user.application.dto;

import jakarta.validation.constraints.NotNull;

public record FollowUserRequestDto
        (@NotNull Long userId,
        @NotNull Long targetUserId) {
}
