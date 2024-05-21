package com.ssafy.controller;

import com.ssafy.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keywords")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @PostMapping("/add")
    public void addKeyword(@RequestBody String keyword) {
        System.out.println("Hello World");
        keywordService.addKeyword(keyword);
    }
}
