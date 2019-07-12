package com.jornadas.client.usuario;

import com.jornadas.client.excepciones.FechaInvalidaException;
import com.jornadas.client.extras.Fecha;

public class Asistente extends Usuario{

	protected Fecha FechaDeNacimiento;
	protected String LU;
	protected String Universidad;
	protected String Carrera;
	protected String Nacionalidad;
	protected String Grupo;
	//protected TipoAsistente TipoAsistente;
	//protected List<Seleccionable> Caracteristicas;
	//protected List<Seleccionable> Servicios;
	//protected List<Actividad> Actividades;

	//Constructor
	
	protected Asistente(String id, String dni) {
		super(id, dni);
	}

	//Metodos
	
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
	
	public void establecerNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	
	public void establecerGrupo(String grupo) {
		Grupo = grupo;
	}
	
	//Consultas
	
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
