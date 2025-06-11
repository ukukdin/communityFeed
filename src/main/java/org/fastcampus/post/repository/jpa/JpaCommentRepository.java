package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value="UPDATE CommentEntity c " +
            "SET c.content = :#{#comment.getContent()}," +
            "c.updDt = now() " +
            "WHERE c.id = :#{#comment.getId()}")
    void updateCommentEntity(CommentEntity comment);

    @Modifying
    @Query(value = "UPDATE CommentEntity c " +
            "SET c.likeCount = :#{#commentEntity.likeCount}," +
            "c.updDt = now() " +
            "WHERE c.id = :#{#commentEntity.getId()}")
    void updateLikeCount(CommentEntity commentEntity);

}
