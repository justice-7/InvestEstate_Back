package com.ssafy.service.user;


import com.ssafy.dao.user.RealtorDao;
import com.ssafy.dto.auth.PasswordResetRequest;
import com.ssafy.dto.user.User;
import com.ssafy.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RealtorService {

    private final RealtorDao realtorDao;
    private final EmailUtil emailUtil;

    @Transactional
    public void resetPassword(PasswordResetRequest request) {
        // 이메일로 부동산 업자 정보 조회
        User realtor = realtorDao.findByEmail(request.getEmail());
        if (realtor == null) {
            throw new IllegalArgumentException("No realtor found with the given email.");
        }

        // 임시 비밀번호 생성
        String temporaryPassword = UUID.randomUUID().toString().substring(0, 8);

        // 비밀번호 업데이트
        realtorDao.updatePassword(realtor.getUserId(), temporaryPassword);

        // 임시 비밀번호 이메일 발송
        emailUtil.sendEmail(request.getEmail(), "[MinseoHome] 비밀번호 변경 안내",
                "임시 비밀번호 " + temporaryPassword);
    }
}
