package com.vaadin.demo.domain;

import java.io.Serializable;
import java.util.Date;

public class Cartera implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1471963871193863834L;
	private Date fecha;
	private String especie;
	private Integer usuario;

	private Double saldo;
	private Double valor;
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getNominal() {
		return nominal;
	}
	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}
	private Double nominal;
	

	
	public Cartera(){
		
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	
}
