package com.ssafy.service.user;

import com.ssafy.dao.user.UserDao;
import com.ssafy.dto.user.UserInfoResponse;
import com.ssafy.dto.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao userDao;

    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(Long userId) {
        return userDao.findUserInfoById(userId);
    }

    public void updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        userDao.updateUser(userId, userUpdateRequest);
    }
}
