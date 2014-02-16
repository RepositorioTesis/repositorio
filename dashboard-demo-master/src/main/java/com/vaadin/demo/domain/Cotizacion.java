package com.vaadin.demo.domain;

import java.util.Date;
import java.util.List;

import utils.HibernateUtil;

public class Cotizacion {
	private Integer cotizacion;
	private Double valor;
	private String especie;
	private Date timestamp;
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public static Double getCotizacion(String especie){
		return ((List<Cotizacion>) HibernateUtil.getEntity("FROM Cotizacion WHERE especie='"+especie+"' ORDER BY fecha desc LIMIT 1")).get(0).getValor();
	}
	public Integer getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(Integer cotizacion) {
		this.cotizacion = cotizacion;
	}
	
}
