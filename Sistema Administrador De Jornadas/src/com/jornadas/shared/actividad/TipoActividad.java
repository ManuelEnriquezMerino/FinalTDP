package com.jornadas.shared.actividad;

import java.io.Serializable;

public class TipoActividad implements Serializable{

	private static final long serialVersionUID = 8411793015043295603L;
	protected String ID;
	protected String Titulo;
	protected String Descripcion;
	
	public TipoActividad() {}
	
	public TipoActividad(String id, String titulo, String descripcion) {
		ID = id;
		Titulo = titulo;
		Descripcion = descripcion;
	}
	
	public void establecerID(String id) {
		ID = id;
	}
	
	public void establecerTitulo(String titulo) {
		Titulo = titulo;
	}
	
	public void establecerDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	public String obtenerID() {
		return ID;
	}
	
	public String obtenerTitulo() {
		return Titulo;
	}
	
	public String obtenerDescripcion() {
		return Descripcion;
	}
}
