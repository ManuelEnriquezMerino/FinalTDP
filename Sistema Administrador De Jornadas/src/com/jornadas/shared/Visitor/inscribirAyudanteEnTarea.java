package com.jornadas.shared.Visitor;

import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;
import com.jornadas.shared.tarea.Tarea;

public class inscribirAyudanteEnTarea extends Accion{

	private static final long serialVersionUID = -3511804735336508561L;
	protected Tarea Tarea;
	protected boolean resultado;
	
	public inscribirAyudanteEnTarea(Tarea tarea) {
		Tarea=tarea;
		resultado = false;
	}
	
	public void Accionar(Asistente usuario) {}

	public void Accionar(Voluntario usuario) {
		inscribir(usuario);
	}

	public void Accionar(Organizador usuario) {
		inscribir(usuario);
	}

	public void Accionar(CoordinadorDeArea usuario) {
		inscribir(usuario);
	}

	public void Accionar(OrganizadorGeneral usuario) {
		inscribir(usuario);
	}
	
	protected void inscribir(Voluntario usuario) {
		if(Tarea.estaDisponible()) {
			Tarea.establecerVoluntario(usuario);
			usuario.agregarTarea(Tarea);
			resultado = true;
		}
	}
	
	public boolean resultadoInscripcion() {
		return resultado;
	}
}
