package com.jornadas.shared.usuario;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.jornadas.shared.actividad.Tarea;

public class Voluntario extends Usuario{
	
	protected List<Tarea> Tareas;
	private static final long serialVersionUID = 3298967923039628962L;

	public Voluntario() {}
	
	public Voluntario(String id, String dni) {
		super(id, dni);
		Tareas = new LinkedList<Tarea>();
	}

	public void agregarTarea(Tarea t) {
		Tareas.add(t);
	}
	
	public Iterator<Tarea> obtenerTareas(){
		return Tareas.iterator();
	}
}
