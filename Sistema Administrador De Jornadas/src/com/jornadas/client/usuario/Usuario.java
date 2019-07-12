package com.jornadas.client.usuario;

public abstract class Usuario {

	protected String ID;
	protected String DNI;
	protected String Nombre;
	protected String Apellido;
	protected DatosDeContacto DatosDeContacto;
	
	//Constructor
	
	protected Usuario(String id, String dni) {
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
