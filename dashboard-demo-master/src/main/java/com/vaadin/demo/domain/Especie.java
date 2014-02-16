package com.vaadin.demo.domain;

import java.util.List;

import utils.HibernateUtil;

public class Especie {
	private String especie;
	private Integer especiedetalle;
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public Integer getespeciedetalle() {
		return especiedetalle;
	}
	public void setespeciedetalle(Integer detalle) {
		this.especiedetalle = detalle;
	}

	public static List<Especie> getAll(){
		return (List<Especie>) HibernateUtil.getEntity("FROM Especie");
	}

}


