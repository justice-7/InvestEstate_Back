package com.ssafy.dto.apt;

import lombok.Data;
import java.util.List;

@Data
public class AptDeal {
    private Long aptDealId;
    private Long userId;
    private String price;
    private String builtYear;
    private String year;
    private String dongName;
    private String month;
    private String day;
    private String area;
    private String jibun;
    private String regionCode;
    private Integer floor;
    private String name;
    private String lat;
    private String lng;
    private String dongCode;
    private Long aptId;
    private String content; // 추가됨
    private List<String> imageUrls;

    public String getDate() {
        return year + "-" + month + "-" + day;
    }
}
