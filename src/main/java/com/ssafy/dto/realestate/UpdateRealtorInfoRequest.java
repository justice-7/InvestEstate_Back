package com.ssafy.dto.realestate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRealtorInfoRequest {
    private String name;
    private String phoneNumber;
    private String realEstateName;
    private String realEstateLocation;
}