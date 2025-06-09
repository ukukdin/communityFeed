package org.fastcampus.user.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.user.domain.UserInfo;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class User {

    //유저 정보의 유효성 정보

    private Long id;
    private UserInfo userInfo;;
    private PositiveIntegerCounter followingCount;
    private PositiveIntegerCounter followerCounter;

    public User(Long id,UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) throws IllegalAccessException {
        if(targetUser.equals(this)){
            throw new IllegalAccessException();
        }
        followingCount.increase();
        targetUser.increaseFollowerCounter();
    }
    public void unfollow(User targetUser) throws IllegalAccessException {
        if(this.equals(targetUser)){
           throw new IllegalAccessException();
        }
        followingCount.decrease();
        targetUser.decreaseFollowerCounter();
    }

    //demeter의 객체 지향 법칙으로 캡슐화를 진행했다.
    private void increaseFollowerCounter() {
        followerCounter.increase();
    }

    private void decreaseFollowerCounter() {
        followerCounter.decrease();
    }
    //컨트럴
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public PositiveIntegerCounter getFollowerCounter() {
        return followerCounter;
    }

    public PositiveIntegerCounter getFollowingCount() {
        return followingCount;
    }

    public int followerCounter() {
        return followerCounter.getCount();
    }
    public int followingCounter() {
        return followingCount.getCount();
    }
    public String getProfileImage() {
        return userInfo.getProfileImageUrl();
    }

    public String getName(){
        return userInfo.getName();
    }
}
