package com.vaadin.demo.domain;

import java.util.List;

import org.hibernate.Session;

import utils.HibernateUtil;


public class Usuario {

	private Integer idusuario;
	private String email;
	private String password;
	private Boolean eliminado;
	
	public Usuario(){
		
	}
	
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public static Integer Login(String usuario, String pass) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
        List<Usuario> result = (List<Usuario>)session.createQuery("from Usuario where email='"+usuario+"' and password='"+pass+"'").list();
        session.getTransaction().commit();
        if(result.isEmpty())
        	return null;
        return result.get(0).getIdusuario();
	}
	
	
}
