package com.fiap.drones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.drones.models.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String>{}

