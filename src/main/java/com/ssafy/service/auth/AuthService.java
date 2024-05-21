package com.ssafy.service.auth;

import com.ssafy.config.JwtTokenProvider;
import com.ssafy.dao.realestate.RealEstateDao;
import com.ssafy.dao.user.UserDao;
import com.ssafy.dto.auth.LoginRequest;
import com.ssafy.dto.auth.RealtorSignUpRequest;
import com.ssafy.dto.auth.SignUpRequest;
import com.ssafy.dto.auth.TokenResponse;
import com.ssafy.dto.realestate.RealEstate;
import com.ssafy.dto.realestate.UpdateRealtorInfoRequest;
import com.ssafy.dto.user.User;
import com.ssafy.exception.DuplicateEmailException;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDao userDao;
    private final RealEstateDao realEstateDao;
    private final com.ssafy.dao.userrealestate.UserRealEstateDao userRealEstateDao;

    public TokenResponse login(LoginRequest loginRequest) {
        User user = userDao.findByEmail(loginRequest.getEmail());
        System.out.println(user);
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            String accessToken = jwtTokenProvider.generateAccessToken(user.getUserId(), user.getRole());
            String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUserId());
            return new TokenResponse(accessToken, refreshToken);
        }
        return null;
    }

    public void signUp(SignUpRequest signUpRequest) {
        // 이메일 중복 검사
        if (userDao.findByEmail(signUpRequest.getEmail()) != null) {
            throw new DuplicateEmailException();
        }
        userDao.save(signUpRequest);
    }

    @Transactional
    public TokenResponse realtorSignUp(RealtorSignUpRequest signUpRequest) {
        // 이메일 중복 검사
        if (userDao.findByEmail(signUpRequest.getEmail()) != null) {
            throw new DuplicateEmailException();
        }

        // User 저장
        userDao.saveRealtor(signUpRequest);
        Long userId = signUpRequest.getUserId();

        // Real Estate 저장
        RealEstate realEstate = new RealEstate();
        realEstate.setName(signUpRequest.getRealEstateName());
        realEstate.setRegistrationNumber(signUpRequest.getRegistrationNumber());
        realEstate.setLocation(signUpRequest.getLocation());
        realEstateDao.save(realEstate);
        Long realEstateId = realEstate.getRealEstateId();

        // User와 Real Estate 연결
        userRealEstateDao.save(userId, realEstateId);

        // 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(userId, signUpRequest.getRole());
        String refreshToken = jwtTokenProvider.generateRefreshToken(userId);

        return new TokenResponse(accessToken, refreshToken);
    }


    @Transactional
    public void updateRealtorInfo(UpdateRealtorInfoRequest updateRequest) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userDao.findById(userId);

        if (user != null && "REA".equals(user.getRole())) {
            userDao.update(userId, updateRequest.getName(), updateRequest.getPhoneNumber());

            RealEstate realEstate = realEstateDao.findByUserId(userId);
            if (realEstate != null) {
                realEstateDao.update(realEstate.getRealEstateId(), updateRequest.getRealEstateName(), updateRequest.getRealEstateLocation());
            }
        } else {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}