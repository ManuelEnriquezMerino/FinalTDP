package com.jornadas.client.Ventanas;

import com.google.gwt.user.client.ui.Panel;

public abstract class Ventana {

	protected String Nombre;
	
	public String obtenerNombre() {
		return Nombre;
	}
	
	public abstract Panel obtenerPanel();
}
