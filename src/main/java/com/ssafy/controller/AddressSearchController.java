package com.ssafy.controller;

import com.ssafy.dto.apt.JoinResult;
import com.ssafy.service.apt.AddressSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class AddressSearchController {

    private final AddressSearchService addressSearchService;

    @GetMapping("/api/address/search")
    public List<JoinResult> searchAddress(@RequestParam String query) {
        return addressSearchService.searchAddress(query);
    }

    @GetMapping("/api/address/details")
    public JoinResult getAddressDetails(@RequestParam Long aptId) {
        return addressSearchService.getAddressDetails(aptId);
    }
}
