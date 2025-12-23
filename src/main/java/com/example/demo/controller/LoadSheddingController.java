package com.example.demo.controller;

import com.example.demo.service.LoadSheddingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/load-shedding")
public class LoadSheddingController {

    private final LoadSheddingService loadSheddingService;

    public LoadSheddingController(LoadSheddingService loadSheddingService) {
        this.loadSheddingService = loadSheddingService;
    }

    @PostMapping("/{zoneId}")
    public String applyLoadShedding(@PathVariable Long zoneId) {
        return loadSheddingService.applyLoadShedding(zoneId);
    }
}
