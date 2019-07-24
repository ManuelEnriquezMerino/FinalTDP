package com.jornadas.shared.usuario;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.jornadas.shared.Visitor.Accion;
import com.jornadas.shared.tarea.Tarea;

public class Voluntario extends Usuario{
	
	protected Collection<Tarea> Tareas;
	private static final long serialVersionUID = 3298967923039628962L;

	public Voluntario() {
		super();
		Tareas = new LinkedHashSet<Tarea>();
	}
	
	public Voluntario(String id, String dni) {
		super(id, dni);
		Tareas = new LinkedHashSet<Tarea>();
	}

	public void agregarTarea(Tarea t) {
		Tareas.add(t);
	}
	
	public boolean quitarTarea(Tarea t) {
		return Tareas.remove(t);
	}
	
	public Collection<Tarea> obtenerTareas(){
		return Tareas;
	}
	
	public void accionar(Accion accion) {
		accion.Accionar(this);
	}
}
