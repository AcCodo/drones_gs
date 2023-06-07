package com.fiap.drones.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Drone {

	@Id
	private String id;
	
	private String modelo, numeroSerie, capacidadeCarga;
	
	private LocalDate dataCompra;
	
	private int capacidadeBateria;
	
	private Long licencaVoo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numerioSerie) {
		this.numeroSerie = numerioSerie;
	}

	public String getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public void setCapacidadeCarga(String capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public int getCapacidadeBateria() {
		return capacidadeBateria;
	}

	public void setCapacidadeBateria(int capacidadeBateria) {
		this.capacidadeBateria = capacidadeBateria;
	}

	public Long getLicencaVoo() {
		return licencaVoo;
	}

	public void setLicencaVoo(Long licencaVoo) {
		this.licencaVoo = licencaVoo;
	}
	
}
