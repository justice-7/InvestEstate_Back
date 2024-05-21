package com.ssafy.dto.advertisement;

import lombok.Data;

@Data
public class Advertisement {
    private Long adId;
    private String title;
    private String imageUrl;
    private String link;
}