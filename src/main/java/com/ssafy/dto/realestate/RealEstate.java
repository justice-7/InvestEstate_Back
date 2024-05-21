package com.ssafy.dto.realestate;

import lombok.Data;

@Data
public class RealEstate {
    private Long realEstateId;
    private String name;
    private String registrationNumber;
    private String location;
}
