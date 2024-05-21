package com.ssafy.service.user;

import com.ssafy.dao.user.UserDao;
import com.ssafy.dto.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao userDao;

    public void updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        userDao.updateUser(userId, userUpdateRequest);
    }
}
