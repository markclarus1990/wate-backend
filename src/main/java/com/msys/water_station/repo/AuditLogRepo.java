package com.msys.water_station.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.AuditLog;

public interface AuditLogRepo extends JpaRepository<AuditLog, Long> {

}
