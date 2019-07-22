package com.jornadas.shared.usuario.creadoresDeOrganizadores;

import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class CreadorOrganizadorGeneral extends CreadorAyudanteEvento{

	private static final long serialVersionUID = -1573800972781408945L;

	public String obtenerNombre() {
		return "Organizador General";
	}

	public String obtenerPrefijoID() {
		return "OG";
	}
	
	public Voluntario crearAyudante() {
		return new OrganizadorGeneral();
	}
}
