package com.xuitter.xuitter.controller;

import com.xuitter.xuitter.dto.UserPageDTO;
import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.service.UserService;
import com.xuitter.xuitter.service.XuitterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class XuitterController {

    private final XuitterService service;
    private final UserService userService;

    public XuitterController(XuitterService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<Tweet>> list(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.listRecentTweets(pageable));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserPageDTO> listTweetsByUser(@PathVariable String username,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.userService.getUserPage(username, pageable));
    }
}
