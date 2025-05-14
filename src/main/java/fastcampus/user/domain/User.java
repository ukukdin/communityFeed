package fastcampus.user.domain;

import java.util.Objects;

public class User {

    //유저 정보의 유효성 정보
    private final Long id;
    private final UserInfo info;;
    private final UserRelationCounter followingCount;
    private final UserRelationCounter followerCounter;

    public User(Long id,UserInfo userInfo) {
        this.id = id;
        this.info = userInfo;
        this.followingCount = new UserRelationCounter();
        this.followerCounter = new UserRelationCounter();
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
}
