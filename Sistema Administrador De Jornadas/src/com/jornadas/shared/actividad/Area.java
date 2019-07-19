package com.jornadas.shared.actividad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;

public class Area implements Serializable{

	private static final long serialVersionUID = 1069853996510760262L;
	protected String ID;
	protected String Nombre;
	protected CoordinadorDeArea Coordinador;
	protected Collection<Organizador> Organizadores;
	protected Collection<TipoActividad> TiposDeActividadesDelArea;
	protected Collection<Actividad> Actividades;
	
	//Constructor
	
	public Area() {
		Organizadores = new LinkedHashSet<Organizador>();
		TiposDeActividadesDelArea = new LinkedHashSet<TipoActividad>();
		Actividades = new LinkedHashSet<Actividad>();
	}
	
	
	//Metodos
	
	public void establecerID(String id) {
		ID = id;
	}
	
	public void establecerNombre(String nombre) {
		Nombre = nombre;
	}
	
	/**
	*	Establece a un Coordinador como el Coordinador del area, y le establece su Area como esta area
	*	@return verdadero si el coordinador no tenia area asignada y el area no teania ya un coordinador, caso contrario falso
	*/
	public boolean establecerCoordinador(CoordinadorDeArea coordinador) {
		if(Coordinador==null && coordinador.establecerArea(this)) {
			Coordinador = coordinador;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	*	Quita al Coordinador del area y se la desasigna
	*	@return verdadero si el area tenia un Coordinador y se lo pudo remover, caso contrario falso
	*/
	public boolean quitarCoordinador() {
		if(Coordinador!=null) {
			Coordinador.quitarArea();
			Coordinador = null;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	*	Agrega un Organizador al area, y le establece su Area como esta area
	*	@return verdadero si el organizador no tenia area asignada y se le pudo establecer la nueva area, caso contrario falso
	*/
	public boolean agregarOrganizador(Organizador organizador) {
		if(organizador.establecerArea(this)) {
			Organizadores.add(organizador);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	*	Quita a un Organizador del area y se la desasigna
	*	@return verdadero si el organizador pertenecia al area y se le pudo remover, caso contrario falso
	*/
	public boolean quitarOrganizador(Organizador organizador) {
		if(Organizadores.remove(organizador)) {
			organizador.quitarArea();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	*	Agrega un tipo de actividad al area
	*	@return verdadero si el tipo de actividad no estaba ya asociado area, caso contrario falso
	*/
	public boolean agregarTipoDeActividad(TipoActividad tipo) {
		return TiposDeActividadesDelArea.add(tipo);
	}
	
	/**
	*	Quita a un tipo de actividad del area
	*	@return verdadero si el tipo de actividad pertenecia al area, caso contrario falso
	*/
	public boolean quitarTipoDeActividad(TipoActividad tipo) {
		return TiposDeActividadesDelArea.remove(tipo);
	}
	
	/**
	*	Agrega una actividad al area
	*	@return verdadero si la actividad no estaba ya asociada area, caso contrario falso
	*/
	public boolean agregarActividad(Actividad actividad) {
		return Actividades.add(actividad);
	}
	
	/**
	*	Quita a una actividad del area
	*	@return verdadero si la actividad pertenecia al area y la removio, caso contrario falso
	*/
	public boolean quitarActividad(Actividad actividad) {
		return Actividades.remove(actividad);
	}
	
	//Consultas
	
	public String obtenerID() {
		return ID;
	}
	
	public String obtenerNombre() {
		return Nombre;
	}
	
	public CoordinadorDeArea obtenerCoordinador() {
		return Coordinador;
	}
	
	public Iterator<Organizador> obtenerOrganizadores() {
		return Organizadores.iterator();
	}
	
	public Iterator<TipoActividad> obtenerTiposDeActividades() {
		return TiposDeActividadesDelArea.iterator();
	}
	
	public Iterator<Actividad> obtenerActividades() {
		return Actividades.iterator();
	}
}
