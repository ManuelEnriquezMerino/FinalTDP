package com.jornadas.shared.usuario;

import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.extras.Fecha;

public class Organizador extends Usuario{
	
	private static final long serialVersionUID = 1384555305901560482L;
	protected Fecha FechaDeNacimiento;
	protected String LU;
	protected String Universidad;
	protected String Carrera;
	//protected Area Area;

	public Organizador() {}
	
	public Organizador(String id, String dni) {
		super(id, dni);
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
