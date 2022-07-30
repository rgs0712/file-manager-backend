package com.rgs.filemanagerbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class HelloController {

    @GetMapping()
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("Test");
    }

}
