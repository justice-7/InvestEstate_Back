package com.ssafy.dto.favorite;

import com.ssafy.dto.apt.AptDeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    private Long userId;
    private Long aptDealId;
    private AptDeal aptDeal;
}
