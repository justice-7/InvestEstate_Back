package com.ssafy.dto.auth;

import lombok.Data;

@Data
public class RealtorSignUpRequest {
    private Long userId; // 추가
    private String name;
    private String email;
    private String password;
    private String role;
    private String phonenumber;
    private String realEstateName;
    private String registrationNumber;
    private String location;
}
