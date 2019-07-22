package com.jornadas.shared.usuario.creadoresDeOrganizadores;

import com.jornadas.shared.usuario.Voluntario;

public class CreadorVoluntario extends CreadorAyudanteEvento{

	private static final long serialVersionUID = -5745959309041598785L;

	public String obtenerNombre() {
		return "Voluntario";
	}

	public String obtenerPrefijoID() {
		return "V";
	}
	
	public Voluntario crearAyudante() {
		return new Voluntario();
	}

}
