package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_WhenCreateCommentContent_ThenREturenTextContext(){
        //given
        String contentText = "this is a test content";

        //when
        CommentContent content = new CommentContent(contentText);

        //then
        assertEquals(contentText, content.getContentText());
    }
    @Test
    void givenContentlengthIsOver_WhenCreateCommentContent_thenThrowException() {
        //given
        String content = "a".repeat(101);

        //when them
        assertThrows(IllegalArgumentException.class, ()-> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁","닭","퇽"})
    void givenContentLengthIsOverAndKorean_WhenCreateCommentContent_thenThrowError(String koreanContent){
        //given
        String content = koreanContent.repeat(101);
        //when then
        assertThrows(IllegalArgumentException.class, ()-> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_WhenCreateCommentContent_thenThrowError(String content){
        assertThrows(IllegalArgumentException.class, ()-> new CommentContent(content));
    }
}
