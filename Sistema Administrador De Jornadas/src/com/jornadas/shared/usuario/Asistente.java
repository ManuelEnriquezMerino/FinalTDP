package com.jornadas.shared.usuario;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.jornadas.shared.Visitor.Accion;
import com.jornadas.shared.actividad.Actividad;

public class Asistente extends Usuario {

	private static final long serialVersionUID = -3904737622861838398L;
	
	//protected String Nacionalidad;
	//protected String Grupo;
	
	//protected TipoAsistente TipoAsistente;
	//protected Collection<Seleccionable> Caracteristicas;
	//protected Collection<Seleccionable> Servicios;
	protected Collection<Actividad> Actividades;

	//Constructor	
	
	public Asistente() {
		super();
		Actividades = new LinkedHashSet<Actividad>();
	}
	
	public Asistente(String id, String dni) {
		super(id, dni);
		Actividades = new LinkedHashSet<Actividad>();
	}

	public boolean agregarActividad(Actividad actividad) {
		return Actividades.add(actividad);
	}
	
	public boolean quitarActividad(Actividad actividad) {
		return Actividades.remove(actividad);
	}

	public void accionar(Accion accion) {
		accion.Accionar(this);
	}
}
