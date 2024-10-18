package com.xuitter.xuitter.controller;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.service.XuitterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class XuitterController {

    private final XuitterService service;

    public XuitterController(XuitterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Page<Tweet>> list(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.listRecentTweets(pageable));
    }
}
