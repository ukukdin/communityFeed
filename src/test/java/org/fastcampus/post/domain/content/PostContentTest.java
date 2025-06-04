package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_WhenCreatedCreated_thenReturnTextcontent() {
        //given
        String text = "this is a test";

        //when
        PostContent content = new PostContent(text);

        //then
        assertEquals(text, content.contentText);

    }

    @Test
    void givenContentLengthIsOver_WhenCreatedCreated_thenThrowError() {
        //given
        String content = "a".repeat(501);

        //when
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁","닭","뷜","싥","슭"})
    void givenContentLengthIsOverKorean_WhenUpdatedCreated_thenTextcontent(String koreanWords) {
        String content = koreanWords.repeat(501);

        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));

    }

    @Test
    void givenContentLengthIsUnder_WhenCreated_thenThrowError() {
        //given
        String content ="a".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_WhenCreated_thenThrowError(String value){
        //when, given
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));

    }
    @Test
    void givenContentLengthIsEmpty_WhenUpdatedCreated_thenNotThrowError() {
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        String updatedContent = "this is updated content";
        postContent.updateContent(updatedContent);
        assertEquals(updatedContent, postContent.contentText);

    }
    @Test
    void givenContentLengthIsOk_WhenUpdated_thenReturnUpdateContent() {
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);
        String updatedContent = "this is updated content";
        postContent.updateContent(updatedContent);
        assertEquals(updatedContent, postContent.contentText);
    }

    @Test
    void givenContentLengthIsOver_WhenUpdated_thenThrowError() {
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

       String value = "a".repeat(501);
       assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
    }

    @Test
    void givenContentLengthIsUnder_WhenUpdated_thenThrowError() {
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        String value = "a".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
    }

}

