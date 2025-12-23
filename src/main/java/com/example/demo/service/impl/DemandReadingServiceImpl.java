package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.DemandReadingService;
import java.time.Instant;
import java.util.List;

public class DemandReadingServiceImpl implements DemandReadingService {

    private final DemandReadingRepository readingRepo;
    private final ZoneRepository zoneRepo;

    public DemandReadingServiceImpl(DemandReadingRepository r, ZoneRepository z) {
        this.readingRepo = r;
        this.zoneRepo = z;
    }

    public DemandReading createReading(DemandReading r) {
        Zone zone = zoneRepo.findById(r.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (r.getDemandMW() < 0)
            throw new BadRequestException(">= 0");

        if (r.getRecordedAt().isAfter(Instant.now()))
            throw new BadRequestException("future");

        r.setZone(zone);
        return readingRepo.save(r);
    }

    public List<DemandReading> getReadingsForZone(Long zoneId) {
        zoneRepo.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return readingRepo.findByZoneIdOrderByRecordedAtDesc(zoneId);
    }

    public DemandReading getLatestReading(Long zoneId) {
        return readingRepo.findFirstByZoneIdOrderByRecordedAtDesc(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("No readings"));
    }

    public List<DemandReading> getRecentReadings(Long zoneId, int limit) {
        List<DemandReading> list = getReadingsForZone(zoneId);
        return list.subList(0, Math.min(limit, list.size()));
    }
}
