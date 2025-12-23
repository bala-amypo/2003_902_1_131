package com.example.demo.service;

import com.example.demo.entity.DemandReading;
import java.util.List;

public interface DemandReadingService {

    // Create/save a new reading
    DemandReading createReading(DemandReading reading);

    // Get all readings for a specific zone
    List<DemandReading> getReadingsForZone(Long zoneId);

    // Get latest reading for a zone
    DemandReading getLatestReading(Long zoneId);

    // Get recent 'n' readings for a zone
    List<DemandReading> getRecentReadings(Long zoneId, int n);
}
