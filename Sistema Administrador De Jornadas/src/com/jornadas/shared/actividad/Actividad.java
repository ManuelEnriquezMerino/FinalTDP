package com.jornadas.shared.actividad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import com.jornadas.shared.excepciones.CupoInvalidoException;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.excepciones.HoraInvalidaException;
import com.jornadas.shared.excepciones.HorariosEventoInvalidosException;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.usuario.Asistente;

public class Actividad implements Serializable{

	private static final long serialVersionUID = -3772106629823538799L;
	protected String ID;
	protected String Titulo;
	protected String Descripcion;
	protected TipoActividad TipoActividad;
	protected InformacionActividad InformacionActividad;
	protected Collection<Asistente> Asistentes;
	
	//Constructor
	
	public Actividad() {
		InformacionActividad = new InformacionActividad();
		Asistentes = new LinkedHashSet<Asistente>();
	}
	
	
	//Metodos
	
	public void establecerID(String id) {
		ID = id;
	}
	
	public void establecerTitulo(String titulo) {
		Titulo = titulo;
	}
	
	public void establecerDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	public void establecerTipoActividad(TipoActividad tipo) {
		TipoActividad = tipo;
	}
	
	public void establecerHorarioInicio(int HI, int MI) throws HorariosEventoInvalidosException, HoraInvalidaException {
		InformacionActividad.establecerHorarioInicio(HI, MI);
	}
	
	public void establecerHorarioFin(int HF, int MF) throws HorariosEventoInvalidosException, HoraInvalidaException {
		InformacionActividad.establecerHorarioFin(HF, MF);
	}
	
	public void establecerLugar(String lugar) {
		InformacionActividad.establecerLugar(lugar);
	}
	
	public void establecerFecha(int dia, int mes, int anio) throws FechaInvalidaException {
		InformacionActividad.establecerFecha(dia, mes, anio);
	}
	
	public void establecerCupo(int cupo) throws CupoInvalidoException {
		InformacionActividad.establecerCupo(cupo);
	}

	/**
	*	Agrega un Asistente a la actividad
	*	@return verdadero si el Asistente no estaba anotado en la actividad y entonces lo pudo agregar, caso contrario falso
	*/
	public boolean agregarAsistente(Asistente asistente) {
		return Asistentes.add(asistente);
	}
	
	/**
	*	Quita un Asistente de la actividad
	*	@return verdadero si el Asistente estaba anotado y entonces se la pudo quitar, caso contrario falso
	*/
	public boolean quitarAsistente(Asistente asistente) {
		return Asistentes.remove(asistente);
	}
	
	
	//Consultas
	
	public String obtenerID() {
		return ID;
	}
	
	public String obtenerTitulo() {
		return Titulo;
	}
	
	public String obtenerDescripcion() {
		return Descripcion;
	}
	
	public TipoActividad obtenerTipoActividad() {
		return TipoActividad;
	}
	
	public Hora obtenerHorarioInicio() {
		return InformacionActividad.obtenerHorarioInicio();
	}
	
	public Hora obtenerHorarioFin() {
		return InformacionActividad.obtenerHorarioFin();
	}
	
	public String obtenerLugar() {
		return InformacionActividad.obtenerLugar();
	}
	
	public Fecha obtenerFecha() {
		return InformacionActividad.obtenerFecha();
	}
	
	public int obtenerCupo() {
		return InformacionActividad.obtenerCupo();
	}
	
	public Iterator<Asistente> obtenerAsistentes() {
		return Asistentes.iterator();
	}
	
	public boolean hayCupo() {
		return Asistentes.size()<InformacionActividad.obtenerCupo();
	}
}
