package com.ssafy.controller;

import com.ssafy.dto.advertisement.AdvertisementRequest;
import com.ssafy.service.advertisement.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisements")
@CrossOrigin(origins = "*")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @PostMapping
    public ResponseEntity<Void> createAdvertisement(@RequestBody AdvertisementRequest advertisementRequest) {
        System.out.println("Hello");
        advertisementService.createAdvertisement(advertisementRequest);
        return ResponseEntity.status(201).build();
    }
}
