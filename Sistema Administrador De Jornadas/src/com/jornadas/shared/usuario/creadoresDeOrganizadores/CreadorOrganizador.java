package com.jornadas.shared.usuario.creadoresDeOrganizadores;

import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.Voluntario;

public class CreadorOrganizador extends CreadorAyudanteEvento{

	private static final long serialVersionUID = 131333069091681407L;

	public String obtenerNombre() {
		return "Organizador";
	}
	
	public String obtenerPrefijoID() {
		return "O";
	}
	
	public Voluntario crearAyudante() {
		return new Organizador();
	}
}
