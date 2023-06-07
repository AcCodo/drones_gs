package com.fiap.drones.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HistoricoVoo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String idDrone;
	
	private double latitudeInicioVoo, longitudeInicioVoo, latitudeFimVoo, longitudeFimVoo;
	
	private double altitudeMedia, velocidadeMedia;
	
	private LocalDate dataDecolagem, dataAterrissagem;

	public double getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}

	public LocalDate getDataDecolagem() {
		return dataDecolagem;
	}

	public void setDataDecolagem(LocalDate dataDecolagem) {
		this.dataDecolagem = dataDecolagem;
	}

	public LocalDate getDataAterrissagem() {
		return dataAterrissagem;
	}

	public void setDataAterrissagem(LocalDate dataAterrissagem) {
		this.dataAterrissagem = dataAterrissagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdDrone() {
		return idDrone;
	}

	public void setIdDrone(String idDrone) {
		this.idDrone = idDrone;
	}

	public double getLatitudeInicioVoo() {
		return latitudeInicioVoo;
	}

	public void setLatitudeInicioVoo(double latitudeInicioVoo) {
		this.latitudeInicioVoo = latitudeInicioVoo;
	}

	public double getLongitudeInicioVoo() {
		return longitudeInicioVoo;
	}

	public void setLongitudeInicioVoo(double longitudeInicioVoo) {
		this.longitudeInicioVoo = longitudeInicioVoo;
	}

	public double getLatitudeFimVoo() {
		return latitudeFimVoo;
	}

	public void setLatitudeFimVoo(double latitudeFimVoo) {
		this.latitudeFimVoo = latitudeFimVoo;
	}

	public double getLongitudeFimVoo() {
		return longitudeFimVoo;
	}

	public void setLongitudeFimVoo(double longitudeFimVoo) {
		this.longitudeFimVoo = longitudeFimVoo;
	}

	public double getAltitudeMedia() {
		return altitudeMedia;
	}

	public void setAltitudeMedia(double altitudeMedia) {
		this.altitudeMedia = altitudeMedia;
	}
	
}
