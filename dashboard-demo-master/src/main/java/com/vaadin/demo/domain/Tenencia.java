package com.vaadin.demo.domain;

import java.util.List;

import com.vaadin.ui.UI;

import utils.HibernateUtil;

public class Tenencia {

	private Integer tenencia;
	private String especie;
	private Boolean eliminado;
	private Integer usuario;
	public Integer getTenencia() {
		return tenencia;
	}
	public void setTenencia(Integer tenencia) {
		this.tenencia = tenencia;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	static public Tenencia getTenencia(String especie){
		List<Tenencia> tenencia = (List<Tenencia>)HibernateUtil.getEntity("FROM Tenencia WHERE especie='"+especie+"'and usuario='"+UsuarioDetalle.getCurrentUser().getUsuario()+"'"); 
		return tenencia.isEmpty() ? null : tenencia.get(0);
	}
	
	static public List<Tenencia> getAll(){
		return (List<Tenencia>)HibernateUtil.getEntity("FROM Tenencia WHERE usuario='"+UsuarioDetalle.getCurrentUser().getUsuario()+"'"); 
	
	}
}
