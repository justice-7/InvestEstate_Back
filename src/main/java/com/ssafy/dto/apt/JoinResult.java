package com.ssafy.dto.apt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinResult {
    private Integer aptId; // 추가된 필드
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String name;
    private String jibun;
    private String builtYear;
}
