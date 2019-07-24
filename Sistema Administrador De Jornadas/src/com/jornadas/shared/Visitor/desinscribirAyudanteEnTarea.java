package com.jornadas.shared.Visitor;

import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class desinscribirAyudanteEnTarea extends Accion{

	private static final long serialVersionUID = 3925440979697392729L;
	protected Tarea Tarea;
	protected boolean resultado;
	
	public desinscribirAyudanteEnTarea(Tarea tarea) {
		Tarea=tarea;
		resultado = false;
	}

	public void Accionar(Asistente usuario) {}

	public void Accionar(Voluntario usuario) {
		desinscribir(usuario);
	}

	public void Accionar(Organizador usuario) {
		desinscribir(usuario);
	}

	public void Accionar(CoordinadorDeArea usuario) {
		desinscribir(usuario);
	}

	public void Accionar(OrganizadorGeneral usuario) {
		desinscribir(usuario);
	}
	
	protected void desinscribir(Voluntario usuario) {
		resultado = Tarea.obtenerVoluntario().obtenerDNI().equals(usuario.obtenerDNI()) && Tarea.obtenerVoluntario().obtenerID().equals(usuario.obtenerID());
		if(resultado) {
			Tarea.quitarVoluntario();
			resultado = usuario.quitarTarea(Tarea);
		}
	}
	
	public boolean resultadoDesinscripcion() {
		return resultado;
	}
}
