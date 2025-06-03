package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter LikeCount;


    public Post(Long id, User author, PostContent content, PositiveIntegerCounter listCount) {

        if(author == null) {
            throw new NullPointerException("author support not null ");
        }
        this.id = id;
        this.author = author; //가독성 좋지만 테스트할때
        this.content = content;
        this.LikeCount = new PositiveIntegerCounter();

    }

    public void like(User user) {
        if(this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        LikeCount.increase();
    }

    public void disLike() {

        this.LikeCount.decrease();
    }

    public void updatePost(User user, String updateContent){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updateContent);
    }
}
