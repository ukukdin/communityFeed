package org.fastcampus.user.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.application.dto.GetUserResponseDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.jpa.JpaUserListPagingQueryRepository;
import org.fastcampus.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JpaUserListQueryRepository userListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }
    //아이디값으로 디비 조회 하는거 (나중에 api 조회할때 유용)
    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable Long userId) {
        return Response.ok(userService.getUserProfile(userId));
    }


    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name="userId") Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowingList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name="userId") Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowerList(userId);
        return Response.ok(result);
    }


}
