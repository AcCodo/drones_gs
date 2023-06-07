package com.fiap.drones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.drones.models.LicencaVoo;

@Repository
public interface LicencaVooRepository extends JpaRepository<LicencaVoo, Long>{}

