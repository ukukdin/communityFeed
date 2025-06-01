package fastcampus.user.application
import fastcampus.user.application.dto.FollowUserRequestDto;
import fastcampus.user.application.interfaces.UserRelationRepository;
import fastcampus.user.domain.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRelationService {

    private final UserRelationRepository userRelationRepository; //서비스를 리포지토리 분리
    private final UserService userService;

    public void followUser(FollowUserRequestDto dto) throws IllegalAccessException {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("Already followed");
        }

        user.follow(targetUser);

        userRelationRepository.save(user, targetUser);
    }

    public void unfollowUser(FollowUserRequestDto dto) throws IllegalAccessException {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("Not followed yet");
        }

        user.unfollow(targetUser);

        userRelationRepository.delete(user, targetUser);
    }
}
