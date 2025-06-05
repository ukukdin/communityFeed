package org.fastcampus.post.repository;

import org.fastcampus.post.application.Interfaces.LikeRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.domain.User;

import java.util.*;

public class FakeLikeRepository implements LikeRepository {

    private final Map<Post, Set<User>> postLikes = new HashMap<>();
    private final Map<Comment,Set<User>> commentLikes = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        if(postLikes.get(post) == null) {
            return false;
        }
        return postLikes.get(post).contains(user);
    }

    @Override
    public void like(Post post, User user) {

        Set<User> users = postLikes.get(post);
        if(users == null) {
            users = new HashSet<>();
        }
        users.add(user);
        postLikes.put(post, users);

    }

    @Override
    public void unlike(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if(users == null) {
            return;
        }
        users.remove(user);
        postLikes.put(post, users);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
       if(commentLikes.get(comment) == null) {
           return false;
       }
       return commentLikes.get(comment).contains(user);
    }

    @Override
    public void like(Comment comment, User user) {
        Set<User> users = postLikes.get(comment);
        if(users == null) {
            users = new HashSet<>();

        }
        users.add(user);
        postLikes.get(comment).add(user);

    }

    @Override
    public void unlike(Comment comment, User user) {
        Set<User> users = postLikes.get(comment);
        if(users == null) {
            return;
        }
        users.remove(user);

        postLikes.get(comment).add(user);
    }
}
