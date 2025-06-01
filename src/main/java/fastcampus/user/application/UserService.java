package fastcampus.user.application;

import fastcampus.user.application.dto.CreateUserRequestDto;
import fastcampus.user.application.dto.GetUserResponseDto;
import fastcampus.user.application.interfaces.UserRepository;
import fastcampus.user.domain.User;
import fastcampus.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserRequestDto dto) throws IllegalAccessException {
        UserInfo userInfo = new UserInfo(dto.userName(), dto.userProfileUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }
}
