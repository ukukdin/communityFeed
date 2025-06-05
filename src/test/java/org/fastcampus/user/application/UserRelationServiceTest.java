package org.fastcampus.user.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRelationServiceTest {


    private final UserService userService =FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService= FakeObjectFactory.getUserRelationService();

    private User user1;
    private User user2;


    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() throws Exception {
        CreateUserRequestDto dto = new CreateUserRequestDto("test","");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved(){

        userRelationService.follow(requestDto);

        assertEquals(1,user1.FollowingCount());
        assertEquals(1,user2.FollowerCount());

    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        //given
        userRelationService.follow(requestDto);
        //when , then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));

    }

    @Test
    void givenCreateOneUser_whenUnFollow_thenUserThrowError() {
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

//    0000000
    @Test
    void givenCreateTwoUser_whenUnFollow_thenUserUnFollowSaved(){

        userRelationService.unfollow(requestDto);

        assertEquals(0,user1.FollowingCount());
        assertEquals(0,user2.FollowerCount());

    }

    @Test
    void givenCreateTwoUserFollowed_whenUnFollow_thenUserThrowError() {
        userRelationService.follow(requestDto);
        //when , then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));

    }

    @Test
    void givenCreateOneUser_wwhenFollowSelf_thenUserThrowError() {
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

}
