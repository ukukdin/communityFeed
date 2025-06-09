package org.fastcampus.user.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

import java.util.List;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followingCount;
    private Integer followerCounter;


    @OneToMany
    private List<PostEntity> posts;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name =user.getName();
        this.profileImage = user.getProfileImage();
        this.followingCount = user.followingCounter();
        this.followerCounter = user.followerCounter();

    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name,profileImage))
                .followerCounter(new PositiveIntegerCounter(followerCounter))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();

    }
}
