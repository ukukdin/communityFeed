package org.fastcampus.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInfoTest {

    @Test
    void givenNameAndProfileImage_whenCreated_thenThrowNothing() throws IllegalArgumentException {
        //given
        String name = "abcd";
        String profileImageUrl = "";
        //when
        //then
        assertDoesNotThrow(()-> new UserInfo(name, profileImageUrl));

    }

    @Test
    void givenBlankName_whenCreated_thenThrowError() {
        //given
        String name = "";
        String profileImageUrl = "";

        //when
        //then
        assertThrows(IllegalArgumentException.class, ()-> new UserInfo(name,profileImageUrl));

    }
}
