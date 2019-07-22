package com.jornadas.shared.tarea.creadoresDeTareas;

import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.tarea.TareaExterna;

public class CreadorTareaExterna extends CreadorTarea{

	private static final long serialVersionUID = -3618212009946447263L;

	public CreadorTareaExterna() {
		Nombre = "Tarea Externa";
	}
	
	public Tarea obtenerTarea() {
		return new TareaExterna();
	}
}
