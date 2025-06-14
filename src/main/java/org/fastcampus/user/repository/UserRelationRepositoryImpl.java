package org.fastcampus.user.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {


    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;

    //jsp user  repository 에서 id를 통해서 존재 여부를 불린값으로 반환하는 메소드 구현 함
    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }


    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

    @Override
    @Transactional
    public void delete(User user,  User targetUser) {
        UserRelationEntity id = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.delete(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
