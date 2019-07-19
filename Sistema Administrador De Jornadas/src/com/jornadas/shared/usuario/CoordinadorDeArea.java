package com.jornadas.shared.usuario;

import com.jornadas.shared.Visitor.Accion;

public class CoordinadorDeArea extends Organizador{

	private static final long serialVersionUID = 6104568222723942758L;

	public CoordinadorDeArea() {
		super();
	}
	
	public CoordinadorDeArea(String id, String dni) {
		super(id, dni);
	}

	public void accionar(Accion accion) {
		accion.Accionar(this);
	}
}
