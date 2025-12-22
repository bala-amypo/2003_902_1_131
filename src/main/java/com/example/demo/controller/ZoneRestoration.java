package com.example.demo.controller;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.service.ZoneRestorationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restorations")
@RequiredArgsConstructor
public class ZoneRestorationController {

    private final ZoneRestorationService zoneRestorationService;

    @PostMapping
    public ZoneRestorationRecord restore(@RequestBody ZoneRestorationRecord record) {
        return zoneRestorationService.restoreZone(record);
    }

    @GetMapping("/{id}")
    public ZoneRestorationRecord getById(@PathVariable Long id) {
        return zoneRestorationService.getRecordById(id);
    }

    @GetMapping("/zone/{zoneId}")
    public List<ZoneRestorationRecord> getByZone(@PathVariable Long zoneId) {
        return zoneRestorationService.getRecordsForZone(zoneId);
    }
}
