package com.fiap.drones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.drones.models.HistoricoVoo;

@Repository
public interface HistoricoVooRepository extends JpaRepository<HistoricoVoo, Long>{}

