package org.fastcampus.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private  User user1;
    private  User user2;
    private  User followingCount;
    private  User followerCount;
    @BeforeEach
    void init(){
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTwoUsers_whenEquals_thenReturnFalse(){
        //when
        boolean result = user1.equals(user2);

        //then
        assertFalse(result);

    }
    @Test
    void givenTwoSameIdUser_whenEquals_thenReturnTrue(){
        //given
        User sameUser = new User(1L, userInfo);

        //when
        boolean result = user1.equals(sameUser);
        assertTrue(result);
    }

    @Test
    void givenTwoUser_whenHashCode_thenReturnFalse(){

        //when
        int hascode = user1.hashCode();
        int hascode2 = user2.hashCode();

        //then
        assertNotEquals(hascode, hascode2);
    }

    @Test
    void givenTwoSameIdUser_whenHashCode_thenReturnTrue(){
        //given
        User sameUser = new User(1L, userInfo);

        //when
        int hashcode = user1.hashCode();
        int sameUserHashcode = sameUser.hashCode();

        //then
        assertEquals(hashcode, sameUserHashcode);
    }

    @Test
    void givenTwoUser_whenUser1FallowUser2_thenIncreaseUserCount(){
        //when
        user1.follow(user2);

        //then
        assertEquals(1,user1.FollowingCount());
        assertEquals(0,user1.FollowerCount());
        assertEquals(0,user2.FollowingCount());
        assertEquals(1,user2.FollowerCount());
    }
    @Test
    void givenTwoUser1FollowUser2_whenUser1FallowUser2_thenIncreaseUserCount(){
        //when
        user1.follow(user2);

        user1.unfollow(user2);
        //then
        assertEquals(0,user1.FollowingCount());
        assertEquals(0,user1.FollowerCount());
        assertEquals(0,user2.FollowingCount());
        assertEquals(0,user2.FollowerCount());
    }


    @Test
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount(){
        //when
        user1.unfollow(user2);

        //then
        assertEquals(0,user1.FollowingCount());
        assertEquals(0,user1.FollowerCount());
        assertEquals(0,user2.FollowingCount());
        assertEquals(0,user2.FollowerCount());
    }
}
