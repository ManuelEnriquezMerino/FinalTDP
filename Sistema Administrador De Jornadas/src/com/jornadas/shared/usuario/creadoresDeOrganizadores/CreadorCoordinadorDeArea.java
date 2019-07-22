package com.jornadas.shared.usuario.creadoresDeOrganizadores;

import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Voluntario;

public class CreadorCoordinadorDeArea extends CreadorAyudanteEvento{

	private static final long serialVersionUID = 4299924101632960055L;

	public String obtenerNombre() {
		return "Coordinador De Area";
	}
	
	public String obtenerPrefijoID() {
		return "C";
	}

	public Voluntario crearAyudante() {
		return new CoordinadorDeArea();
	}

}
