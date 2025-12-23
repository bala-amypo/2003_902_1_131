package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    List<DemandReading> findByZoneId(Long zoneId);

    DemandReading findTopByZoneIdOrderByRecordedAtDesc(Long zoneId);

    @Query("SELECT r FROM DemandReading r WHERE r.zone.id = ?1 ORDER BY r.recordedAt DESC")
    List<DemandReading> findTopByZoneIdOrderByRecordedAtDesc(Long zoneId, Pageable pageable);
}
