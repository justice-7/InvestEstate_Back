package com.ssafy.dao.advertisement;

import com.ssafy.dto.advertisement.AdvertisementRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdvertisementDao {

    @Insert("INSERT INTO advertisement (title, image_url, link) VALUES (#{title}, #{imageUrl}, #{link})")
    void saveAdvertisement(AdvertisementRequest advertisementRequest);
}
