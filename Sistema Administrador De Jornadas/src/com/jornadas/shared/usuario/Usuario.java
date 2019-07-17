	package com.jornadas.shared.usuario;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 4864708511213559500L;
	protected String ID;
	protected String DNI;
	protected String Nombre;
	protected String Apellido;
	protected DatosDeContacto DatosDeContacto;
	
	//Constructor
	public Usuario() {}
	
	public Usuario(String id, String dni) {
		ID = id;
		DNI = dni;
	}
	
	//Comandos
	
	public void establecerID(String id) {
		ID = id;
	}
	
	public void establecerDNI(String dni) {
		DNI = dni;
	}
	
	public void establecerNombre(String nombre) {
		Nombre = nombre;
	}
	
	public void establecerApellido(String apellido) {
		Apellido = apellido;
	}
	
	public void establecerTelefono(String telefono) {
		DatosDeContacto.establecerTelefono(telefono);
	}
	
	public void establecerMail(String mail) {
		DatosDeContacto.establecerMail(mail);
	}
	
	//Consultas
	
	public String obtenerID() {
		return ID;
	}
	
	public String obtenerDNI() {
		return DNI;
	}
	
	public String obtenerNombre() {
		return Nombre;
	}
	
	public String obtenerApellido() {
		return Apellido;
	}
	
	public String obtenerTelefono() {
		return DatosDeContacto.obtenerTelefono();
	}
	
	public String obtenerMail() {
		return DatosDeContacto.obtenerMail();
	}
}
