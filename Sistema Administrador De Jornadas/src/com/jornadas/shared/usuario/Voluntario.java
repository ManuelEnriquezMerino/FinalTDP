package com.jornadas.shared.usuario;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import com.jornadas.shared.actividad.Tarea;

public class Voluntario extends Usuario{
	
	protected Collection<Tarea> Tareas;
	private static final long serialVersionUID = 3298967923039628962L;

	public Voluntario() {}
	
	public Voluntario(String id, String dni) {
		super(id, dni);
		Tareas = new LinkedHashSet<Tarea>();
	}

	public void agregarTarea(Tarea t) {
		Tareas.add(t);
	}
	
	public Iterator<Tarea> obtenerTareas(){
		return Tareas.iterator();
	}
}
