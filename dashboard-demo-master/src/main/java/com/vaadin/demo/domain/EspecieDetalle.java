package com.vaadin.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import utils.HibernateUtil;

public class EspecieDetalle {
	private Integer especiedetalle;
	private Date emision;
	private Date vencimiento;
	private Double nominal;
	private String detalle;
	private String formula;
	private String legislatura;
	private Boolean bucket;
	private String moneda;
	private String formulabucket;
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Transient
	private String especie;
	public Integer getEspeciedetalle() {
		return especiedetalle;
	}
	public void setEspeciedetalle(Integer especiedetalle) {
		this.especiedetalle = especiedetalle;
	}
	public Date getEmision() {
		return emision;
	}
	public void setEmision(Date emision) {
		this.emision = emision;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Double getNominal() {
		return nominal;
	}
	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getLegislatura() {
		return legislatura;
	}
	public void setLegislatura(String legislatura) {
		this.legislatura = legislatura;
	}
	public Boolean getBucket() {
		return bucket;
	}
	public void setBucket(Boolean bucket) {
		this.bucket = bucket;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getFormulabucket() {
		return formulabucket;
	}
	public void setFormulabucket(String formulabucket) {
		this.formulabucket = formulabucket;
	}
	public static List<EspecieDetalle> getAll() {
		return  (List<EspecieDetalle>) HibernateUtil.getEntity("FROM EspecieDetalle");
	}
	public static EspecieDetalle getEspecieDetalle(String especie){
		return ((List<EspecieDetalle>) HibernateUtil.getEntity("FROM EspecieDetalle WHERE especiedetalle='"+especie+"'")).get(0);
	}
	public String getEspecie(){
		if(especie==null){
			especie = Especie.getEspecie(especiedetalle.toString());
		}
		return especie;
	}
	
	public static String getById(String especie){
		return ((List<Especie>) HibernateUtil.getEntity("FROM Especie WHERE especie='"+especie+"'")).get(0).getespeciedetalle().toString();
	}
}
