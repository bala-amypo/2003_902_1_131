package com.example.demo.service.impl;

import com.example.demo.entity.Zone;
import com.example.demo.exception.*;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import java.util.List;

public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repo;

    public ZoneServiceImpl(ZoneRepository repo) {
        this.repo = repo;
    }

    public Zone createZone(Zone zone) {
        if (zone.getPriorityLevel() < 1)
            throw new BadRequestException(">= 1");

        if (repo.findByZoneName(zone.getZoneName()).isPresent())
            throw new BadRequestException("unique");

        return repo.save(zone);
    }

    public Zone updateZone(Long id, Zone zone) {
        Zone existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        existing.setZoneName(zone.getZoneName());
        existing.setPriorityLevel(zone.getPriorityLevel());
        existing.setPopulation(zone.getPopulation());

        return repo.save(existing);
    }

    public Zone getZoneById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    public void deactivateZone(Long id) {
        Zone z = getZoneById(id);
        z.setActive(false);
        repo.save(z);
    }
}
