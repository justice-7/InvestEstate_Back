package com.ssafy.dto.apt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AptInfo {
    private Integer aptId;
    private Integer builtYear;
    private String dongCode;
    private String dongName;
    private String jibun;
    private String name;
    private Double lat;
    private Double lng;
}
