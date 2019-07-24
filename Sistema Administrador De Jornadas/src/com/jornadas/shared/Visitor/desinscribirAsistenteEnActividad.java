package com.jornadas.shared.Visitor;

import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class desinscribirAsistenteEnActividad extends Accion{

	private static final long serialVersionUID = -5839174241655755325L;
	protected Actividad Actividad;
	protected boolean resultado;
	
	public desinscribirAsistenteEnActividad(Actividad actividad) {
		Actividad = actividad;
		resultado = false;
	}
	
	public void Accionar(Asistente usuario) {
		resultado = Actividad.quitarAsistente(usuario);
		if(resultado)
			resultado = usuario.quitarActividad(Actividad);
	}

	public void Accionar(Voluntario usuario) {}

	public void Accionar(Organizador usuario) {}

	public void Accionar(CoordinadorDeArea usuario) {}

	public void Accionar(OrganizadorGeneral usuario) {}

	public boolean resultadoDesinscripcion() {
		return resultado;
	}
}
