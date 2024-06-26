package com.ssafy.controller;

import com.ssafy.dto.apt.AptDeal;
import com.ssafy.dto.apt.AptInfo;
import com.ssafy.service.apt.AptDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apt-deal")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AptDealController {
    private final AptDealService aptDealService;

    @PostMapping
    @PreAuthorize("hasRole('REA')")
    public ResponseEntity<AptDeal> registerAptDeal(@RequestBody AptDeal aptDeal) {
        System.out.println(aptDeal);
        AptDeal registeredAptDeal = aptDealService.registerAptDeal(aptDeal);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredAptDeal);
    }

    @PutMapping("/{aptDealId}")
    @PreAuthorize("hasRole('REA')")
    public ResponseEntity<Void> updateAptDeal(@PathVariable Long aptDealId, @RequestBody AptDeal aptDeal) {
        aptDeal.setAptDealId(aptDealId);
        aptDealService.updateAptDeal(aptDeal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-realtor")
    @PreAuthorize("hasRole('REA')")
    public ResponseEntity<List<AptDeal>> getAptDealsByRealtor() {
        List<AptDeal> aptDeals = aptDealService.getAptDealsByRealtor();
        return ResponseEntity.ok(aptDeals);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AptInfo>> searchAptInfos(
            @RequestParam(value = "priceMin", required = false) Integer priceMin,
            @RequestParam(value = "priceMax", required = false) Integer priceMax,
            @RequestParam(value = "area", required = false) Integer area,
            @RequestParam(value = "searchText", required = false) String searchText) {
        List<AptInfo> aptInfos = aptDealService.searchAptInfos(priceMin, priceMax, area, searchText);
        return ResponseEntity.ok(aptInfos);
    }

    @GetMapping("/related/{aptId}")
    public ResponseEntity<List<AptDeal>> findAptDealsByAptId(@PathVariable Integer aptId) {
        List<AptDeal> aptDeals = aptDealService.findAptDealsByAptId(aptId);
        return ResponseEntity.ok(aptDeals);
    }

    @DeleteMapping("/{aptDealId}")
    @PreAuthorize("hasRole('REA')")
    public ResponseEntity<Void> deleteAptDeal(@PathVariable Long aptDealId) {
        aptDealService.deleteAptDeal(aptDealId);
        return ResponseEntity.noContent().build();
    }
}
