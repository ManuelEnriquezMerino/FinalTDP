package com.jornadas.shared.usuario;

import java.io.Serializable;

public class DatosDeContacto implements Serializable{

	private static final long serialVersionUID = -5812665969254748615L;
	private String Telefono;
	private String Mail;
	
	public DatosDeContacto() {}
	
	public DatosDeContacto(String telefono, String mail) {
		Telefono=telefono;
		Mail=mail;
	}
	
	public void establecerTelefono(String telefono) {
		Telefono=telefono;
	}
	
	public void establecerMail(String mail) {
		Mail=mail;
	}
	
	public String obtenerTelefono() {
		return Telefono;
	}
	
	public String obtenerMail() {
		return Mail;
	}
}
