package com.jornadas.shared.tarea;

public class CreadorTareaExterna extends CreadorTarea{

	private static final long serialVersionUID = -3618212009946447263L;

	public CreadorTareaExterna() {
		Nombre = "Tarea Externa";
	}
	
	public Tarea obtenerTarea() {
		return new TareaExterna();
	}
}
