package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

public interface UserRepository {

    User save(User user);

}
