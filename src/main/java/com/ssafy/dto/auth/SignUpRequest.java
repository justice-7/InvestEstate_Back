package com.ssafy.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phonenumber;
}