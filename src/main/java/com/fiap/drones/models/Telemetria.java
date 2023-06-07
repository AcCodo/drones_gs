package com.fiap.drones.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Telemetria {

	@Id
	private String id;
	
	private String idDrone;
	
	private double latitude, longitude;
	
	//metros/segundo
	private int velocidade;
	
	//0 a 360
	private int direcao;
	
	private LocalDateTime dataHora;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdDrone() {
		return idDrone;
	}

	public void setIdDrone(String idDrone) {
		this.idDrone = idDrone;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
}
