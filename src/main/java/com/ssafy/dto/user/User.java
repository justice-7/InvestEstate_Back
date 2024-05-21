package com.ssafy.dto.user;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phonenumber;
}