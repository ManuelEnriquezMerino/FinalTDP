	package com.jornadas.shared.usuario;

import java.io.Serializable;

import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.extras.Fecha;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 4864708511213559500L;
	protected String ID;
	protected String DNI;
	protected String Nombre;
	protected String Apellido;
	protected String LU;
	protected String Universidad;
	protected String Carrera;
	protected Fecha FechaDeNacimiento;
	protected DatosDeContacto DatosDeContacto;
	
	//Constructor
	public Usuario() {}
	
	public Usuario(String id, String dni) {
		ID = id;
		DNI = dni;
		DatosDeContacto = new DatosDeContacto();
		FechaDeNacimiento = new Fecha();
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
	
	public void establecerFechaDeNacimiento(int dia, int mes, int anio) throws FechaInvalidaException {
		FechaDeNacimiento = new Fecha(dia,mes,anio);
	}
	
	public void establecerLU(String lu) {
		LU = lu;
	}
	
	public void establecerUniversidad(String universidad) {
		Universidad = universidad;
	}
	
	public void establecerCarrera(String carrera) {
		Carrera = carrera;
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
	
	public Fecha obtenerFechaDeNacimiento() {
		return FechaDeNacimiento;
	}
		
	public String obtenerLU() {
		return LU;
	}
		
	public String obtenerUniversidad() {
		return Universidad;
	}
		
	public String obtenerCarrera() {
		return Carrera;
	}
}
