package com.vaadin.demo.domain;

import java.util.Date;

public class Operacion {
	private Integer tenencia;
	private Double valor;
	private Date fecha;
	private Integer operacion; 
	  
	public Integer getOperacion() {
		return operacion;
	}
	public void setOperacion(Integer operacion) {
		this.operacion = operacion;
	}
	public Integer getTenencia() {
		return tenencia;
	}
	public void setTenencia(Integer tenencia) {
		this.tenencia = tenencia;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
