package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.LoadSheddingService;
import java.time.Instant;
import java.util.List;

public class LoadSheddingServiceImpl implements LoadSheddingService {

    private final SupplyForecastRepository forecastRepo;
    private final ZoneRepository zoneRepo;
    private final DemandReadingRepository readingRepo;
    private final LoadSheddingEventRepository eventRepo;

    public LoadSheddingServiceImpl(
            SupplyForecastRepository f,
            ZoneRepository z,
            DemandReadingRepository d,
            LoadSheddingEventRepository e) {

        this.forecastRepo = f;
        this.zoneRepo = z;
        this.readingRepo = d;
        this.eventRepo = e;
    }

    public LoadSheddingEvent triggerLoadShedding(Long forecastId) {

        SupplyForecast forecast = forecastRepo.findById(forecastId)
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));

        List<Zone> zones = zoneRepo.findByActiveTrueOrderByPriorityLevelAsc();

        if (zones.isEmpty())
            throw new BadRequestException("No suitable");

        Zone z = zones.get(0);

        DemandReading r = readingRepo
                .findFirstByZoneIdOrderByRecordedAtDesc(z.getId())
                .orElseThrow(() -> new BadRequestException("No overload"));

        LoadSheddingEvent event = LoadSheddingEvent.builder()
                .zone(z)
                .eventStart(Instant.now())
                .reason("Overload")
                .triggeredByForecastId(forecastId)
                .expectedDemandReductionMW(r.getDemandMW())
                .build();

        return eventRepo.save(event);
    }

    public LoadSheddingEvent getEventById(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    public List<LoadSheddingEvent> getEventsForZone(Long zoneId) {
        return eventRepo.findByZoneIdOrderByEventStartDesc(zoneId);
    }

    public List<LoadSheddingEvent> getAllEvents() {
        return eventRepo.findAll();
    }
}
