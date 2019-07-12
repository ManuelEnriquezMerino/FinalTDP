package com.jornadas.client.usuario;

public class DatosDeContacto {

	private String Telefono;
	private String Mail;
	
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
