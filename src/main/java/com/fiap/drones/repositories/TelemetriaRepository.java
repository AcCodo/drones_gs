package com.fiap.drones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.drones.models.Telemetria;

@Repository
public interface TelemetriaRepository extends JpaRepository<Telemetria, String>{
	
	@Query("select t from Telemetria t where t.idDrone = ?1")
	public List<Telemetria> findByDrone(String droneId);
	
}

