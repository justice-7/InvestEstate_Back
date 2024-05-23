package com.ssafy.dto.favorite;

import com.ssafy.dto.apt.AptInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    private Long favoriteId;
    private Long userId;
    private Long aptId;
    private AptInfo aptInfo; // 아파트 정보를 담는 객체
}
