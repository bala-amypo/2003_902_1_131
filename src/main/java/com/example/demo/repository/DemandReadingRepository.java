package com.example.demo.repository;

import com.example.demo.entity.DemandReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandReadingRepository extends JpaRepository<DemandReading, Long> {

    // Get all readings for a zone
    List<DemandReading> findByZoneId(Long zoneId);

    // Get latest reading
    DemandReading findTopByZoneIdOrderByRecordedAtDesc(Long zoneId);

    // Get top N readings using Pageable
    @Query("SELECT r FROM DemandReading r WHERE r.zone.id = :zoneId ORDER BY r.recordedAt DESC")
    List<DemandReading> findTopNByZoneIdOrderByRecordedAtDesc(@Param("zoneId") Long zoneId, org.springframework.data.domain.Pageable pageable);
}
