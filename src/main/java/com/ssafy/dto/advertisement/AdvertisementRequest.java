package com.ssafy.dto.advertisement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementRequest {
    private String title;
    private String imageUrl;
    private String link;
}
