package org.fastcampus.post.domain.comment;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter LikeCount;
    private PostPublicationState state;

    public static Comment createComment( User author, Post post, String content){
        return new Comment(author, null, post,new CommentContent(content));

    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    public PostPublicationState getState() {
        return state;
    }

    public Comment(User author, Long id, Post post, Content content) {
        if(author == null){
            throw new IllegalArgumentException();
        }

        if(post == null){
            throw new IllegalArgumentException();
        }

        if (content == null){
            throw new IllegalArgumentException();
        }


        this.author = author;
        this.id = id;
        this.post = post;
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

    public void updatePost(User user, String updatedContent){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updatedContent);
    }

    public int getLikeCount() {
        return LikeCount.getCount();
    }

    public String getContent() {
        return content.getContentText() ;
    }

    public Content getContentObject() {
        return content ;
    }

}
