package fastcampus.post.domain.content;

public abstract class Content {
    String contentText;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
    }
    //추상화 하는 행동은 체크를 하는 행동이다.

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
