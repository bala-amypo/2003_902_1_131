package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zone-restoration")
public class ZoneRestorationController {

    @GetMapping
    public String status() {
        return "Zone restoration working";
    }
}
