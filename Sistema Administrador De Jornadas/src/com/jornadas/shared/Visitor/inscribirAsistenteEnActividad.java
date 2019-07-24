package com.jornadas.shared.Visitor;

import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class inscribirAsistenteEnActividad extends Accion{

	private static final long serialVersionUID = -2557781151406155759L;
	protected Actividad Actividad;
	protected boolean resultado;
	
	public inscribirAsistenteEnActividad(Actividad actividad) {
		Actividad = actividad;
		resultado = false;
	}
	
	public void Accionar(Asistente usuario) {
		resultado = Actividad.agregarAsistente(usuario) && usuario.agregarActividad(Actividad);
	}

	public void Accionar(Voluntario usuario) {}

	public void Accionar(Organizador usuario) {}

	public void Accionar(CoordinadorDeArea usuario) {}

	public void Accionar(OrganizadorGeneral usuario) {}

	public boolean resultadoInscripcion() {
		return resultado;
	}
}
