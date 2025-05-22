package fastcampus.post.domain;

import fastcampus.post.domain.content.PostContent;
import fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;

    public Post(Long id,User author, PostContent content) {
        if(author == null) {
            throw new NullPointerException("author support not null ");
        }
        this.id = id;
        this.author = author; //가독성 좋지만 테스트할때
        this.content = content;
    }


}
