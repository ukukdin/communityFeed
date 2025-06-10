package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    User findById(Long id);


}
