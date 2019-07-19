package com.jornadas.shared.usuario;

import com.jornadas.shared.Visitor.Accion;

public class Asistente extends Usuario {

	private static final long serialVersionUID = -3904737622861838398L;
	
	//protected String Nacionalidad;
	//protected String Grupo;
	
	//protected TipoAsistente TipoAsistente;
	//protected List<Seleccionable> Caracteristicas;
	//protected List<Seleccionable> Servicios;
	//protected List<Actividad> Actividades;

	//Constructor	
	
	public Asistente() {
		super();
	}
	
	public Asistente(String id, String dni) {
		super(id, dni);
	}

	public void accionar(Accion accion) {
		accion.Accionar(this);
	}
}
