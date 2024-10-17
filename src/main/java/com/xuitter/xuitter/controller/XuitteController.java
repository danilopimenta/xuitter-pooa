package com.xuitter.xuitter.controller;

import com.xuitter.xuitter.model.Tweet;
import com.xuitter.xuitter.service.XuitterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class XuitteController {
        private XuitterService service;

        public XuitteController(XuitterService service) {
            this.service = service;
        }

        @RequestMapping("/")
        public ResponseEntity<List<Tweet>> list() {
            return ResponseEntity.ok(service.list());
        }
}
