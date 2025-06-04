package org.fastcampus.post.application.Interfaces;

import org.fastcampus.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);


}
