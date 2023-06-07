package com.fiap.drones.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LicencaVoo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String idDrone;
	
	private String codigoLicencaVoo;
	
	private LocalDate dataValidade, dataEmissao;

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

	public String getCodigoLicencaVoo() {
		return codigoLicencaVoo;
	}

	public void setCodigoLicencaVoo(String licensaVoo) {
		this.codigoLicencaVoo = licensaVoo;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
}
