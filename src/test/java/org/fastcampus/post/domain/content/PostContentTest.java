package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_WhenCreatedCreated_thenReturnTextcontent(){
        //given
        String text = "this is a test";

        //when
        PostContent content = new PostContent(text);

        //then
        assertEquals(text, content.contentText);

    }

    @Test
    void givenContentLengthIsOver_WhenCreatedCreated_thenReturnThrowError(){
        //given
        String content = "a".repeat(501);

        //when
        assertThrows(IllegalArgumentException.class, ()-> new PostContent(content));
    }

}
