package com.vaadin.demo.domain;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.demo.dashboard.DashboardUI;
import com.vaadin.ui.UI;

import utils.HibernateUtil;

enum Perfil {	CONSERVADOR_CORTO_PLAZO, 
	ESPECULATIVO_CORTO_PLAZO, 
	CONSERVADOR, 
	CONSERVADOR_MODERADO,
	MODERADO, 
	AGRESIVO_MODERADO,
	AGRESIVO  
}

public class UsuarioDetalle {
	
	private Integer usuario;
	private String perfil;
	private String nombre;
	private String apellido;
	private String saludo;
	private String nota;
	
	
	
	
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	public UsuarioDetalle() {
		this.usuario=1;
		this.nombre="Gabriel";
		this.apellido = "Calabro";
		
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public static UsuarioDetalle getUsuarioDetalle(Integer id){
		return (UsuarioDetalle) HibernateUtil.getEntity("FROM UsuarioDetalle WHERE usuario="+id).get(0);
		
	}
	
	
	
	public String setPerfilDeInversor(Integer t, Integer r) {
		return  (t <= 2 && r <= 25) ? Perfil.CONSERVADOR_CORTO_PLAZO.toString() :
						(t<=2 && r > 25) ? Perfil.ESPECULATIVO_CORTO_PLAZO.toString() : 
						(t<=4 && r <= 18) ? Perfil.CONSERVADOR.toString() :
						(t<=4 && r <= 31) ? Perfil.CONSERVADOR_MODERADO .toString():
						(t<=4)? Perfil.MODERADO.toString() :
						(t<=6 && r <=15) ? Perfil.CONSERVADOR.toString() :
						(t<=6 && r <= 24) ? Perfil.CONSERVADOR_MODERADO.toString() :
						(t<=6 && r <= 35) ? Perfil.MODERADO.toString() :
						(t<=6) ? Perfil.AGRESIVO_MODERADO.toString() :
						(t<=9 && r <= 12) ? Perfil.CONSERVADOR.toString() :
						(t<=9 && r <= 21) ? Perfil.CONSERVADOR_MODERADO.toString() :
						(t<=9 && r <= 28) ? Perfil.MODERADO.toString() :
						(t<=9 && r <= 37) ? Perfil.AGRESIVO_MODERADO.toString():
						(t<=9) ? Perfil.AGRESIVO.toString() :
						(t<=12 && r <= 11) ? Perfil.CONSERVADOR.toString() :
						(t<=12 && r <= 19) ? Perfil.CONSERVADOR_MODERADO.toString() :
						(t<=12 && r <= 26) ? Perfil.MODERADO.toString() :
						(t<=12 && r <= 34) ? Perfil.AGRESIVO_MODERADO.toString() :
						(t<=12) ? Perfil.AGRESIVO.toString() :
						(r <=10)? Perfil.CONSERVADOR.toString() :
						(r <=18)? Perfil.CONSERVADOR_MODERADO.toString() :
						(r <=24)? Perfil.MODERADO.toString() :
						(r <=31)? Perfil.AGRESIVO_MODERADO.toString() :
						Perfil.AGRESIVO.toString();
		
	}
	
	public List<Cartera> obtenerCartera(){
		try {
			return (List<Cartera>)HibernateUtil.getEntity("FROM Cartera "
															 + "WHERE usuario='"+usuario+"'");
		} catch (Exception e) {
			return new ArrayList<Cartera>();
		}
	}
	
	public static UsuarioDetalle getCurrentUser(){
		return ((DashboardUI)UI.getCurrent()).getUser();
	}
	
	public List<Notificacion> obtenerNotificacion(){
		try {
			return (List<Notificacion>)HibernateUtil.getEntity("FROM Notificacion "
															 + "WHERE usuario='"+usuario+"' AND vista=false");
		} catch (Exception e) {
			return new ArrayList<Notificacion>();
		}
	}

}
