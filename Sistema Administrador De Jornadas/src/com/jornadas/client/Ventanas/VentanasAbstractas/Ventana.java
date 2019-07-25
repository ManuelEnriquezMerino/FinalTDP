package com.jornadas.client.Ventanas.VentanasAbstractas;

import com.google.gwt.user.client.ui.Panel;

public abstract class Ventana {

	protected String Nombre;
	
	public void establecerNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String obtenerNombre() {
		return Nombre;
	}

	public abstract Panel obtenerPanel();
}
