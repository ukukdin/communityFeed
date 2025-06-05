package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter LikeCount;
    private PostPublicationState state;


    public static Post createPost(Long id, User user, Content content, PostPublicationState state) {
        return new Post(id, user, content, state);
    }
    

    public static Post createDefaultPost(Long id, User user, String content, PostPublicationState state) {
        return new Post(id, user, new PostContent(content), PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {

        if(author == null) {
            throw new NullPointerException("author support not null ");
        }
        this.id = id;
        this.author = author; //가독성 좋지만 테스트할때
        this.content = content;
        this.LikeCount = new PositiveIntegerCounter();
        this.state = state;
    }
    public Post(Long id, User author, Content content ) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    public int getLikeCount() {
        return LikeCount.getCount();
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

    public void updatePost(User user, String updateContent, PostPublicationState aPublic){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updateContent);
    }

    public Content getContent() {
        return content;
    }
    public PostPublicationState getState() {
        return state;
    }

    public User getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }
}
