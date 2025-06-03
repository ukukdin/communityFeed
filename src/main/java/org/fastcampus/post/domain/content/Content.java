package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DateTimeInfo;

public abstract class Content {

    protected String contentText;
    protected final DateTimeInfo dateTimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.dateTimeInfo = new DateTimeInfo();
    }
    //추상화 하는 행동은 체크를 하는 행동이다.
    public void updateContent(String updateContent){
        checkText(updateContent);
        this.contentText = updateContent;
        this.dateTimeInfo.updateEditDatetime();

    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
