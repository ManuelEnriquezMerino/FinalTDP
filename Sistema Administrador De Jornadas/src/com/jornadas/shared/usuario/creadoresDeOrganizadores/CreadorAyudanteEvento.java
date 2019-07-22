package com.jornadas.shared.usuario.creadoresDeOrganizadores;

import java.io.Serializable;

import com.jornadas.shared.usuario.Voluntario;

public abstract class CreadorAyudanteEvento implements Serializable{

	private static final long serialVersionUID = 5598833158883845171L;

	public abstract String obtenerNombre();
	
	public abstract String obtenerPrefijoID();
	
	public abstract Voluntario crearAyudante();
}
