package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.ZoneRestorationService;
import java.util.List;

public class ZoneRestorationServiceImpl implements ZoneRestorationService {

    private final ZoneRestorationRecordRepository recordRepo;
    private final LoadSheddingEventRepository eventRepo;
    private final ZoneRepository zoneRepo;

    public ZoneRestorationServiceImpl(
            ZoneRestorationRecordRepository r,
            LoadSheddingEventRepository e,
            ZoneRepository z) {

        this.recordRepo = r;
        this.eventRepo = e;
        this.zoneRepo = z;
    }

    public ZoneRestorationRecord restoreZone(ZoneRestorationRecord record) {

        LoadSheddingEvent event = eventRepo.findById(record.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        zoneRepo.findById(record.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (!record.getRestoredAt().isAfter(event.getEventStart()))
            throw new BadRequestException("after event start");

        return recordRepo.save(record);
    }

    public ZoneRestorationRecord getRecordById(Long id) {
        return recordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    public List<ZoneRestorationRecord> getRecordsForZone(Long zoneId) {
        return recordRepo.findByZoneIdOrderByRestoredAtDesc(zoneId);
    }
}
