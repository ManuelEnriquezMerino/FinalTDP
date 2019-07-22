package com.jornadas.shared.tarea.creadoresDeTareas;

import java.io.Serializable;

import com.jornadas.shared.tarea.Tarea;

public abstract class CreadorTarea implements Serializable{

	private static final long serialVersionUID = -8655576915379430007L;
	protected String Nombre;
	
	public String obtenerNombre() {
		return Nombre;
	}
	
	public abstract Tarea obtenerTarea();
}
