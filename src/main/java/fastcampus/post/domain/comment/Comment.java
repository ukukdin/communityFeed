package fastcampus.post.domain.comment;

import fastcampus.common.domain.PositiveIntegerCounter;
import fastcampus.post.domain.Post;
import fastcampus.post.domain.content.Content;
import fastcampus.post.domain.content.PostPublicationState;
import fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter LikeCount;
    private PostPublicationState state;

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

    public void updatePost(User user, String updatedContent, PostPublicationState state){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updatedContent);
    }

}
