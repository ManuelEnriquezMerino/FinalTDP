package com.jornadas.shared.tarea;

import java.io.Serializable;

import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.excepciones.HoraInvalidaException;
import com.jornadas.shared.excepciones.HorariosEventoInvalidosException;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.usuario.Voluntario;

public abstract class Tarea implements Serializable{

	private static final long serialVersionUID = 3224887178289840475L;
	protected String ID;
	protected String Titulo;
	protected String Descripcion;
	protected Voluntario Voluntario;
	protected InformacionTarea InformacionTarea;
	
	public Tarea() {
		InformacionTarea=new InformacionTarea();
	}
	
	public void establecerID(String id) {
		ID=id;
	}
	
	public void establecerTitulo(String titulo) {
		Titulo=titulo;
	}
	
	public void establecerDescripcion(String descripcion) {
		Descripcion=descripcion;
	}
	
	public void establecerVoluntario(Voluntario voluntario) {
		Voluntario=voluntario;
	}
	
	public void establecerHorarioInicio(int HI, int MI) throws HorariosEventoInvalidosException, HoraInvalidaException {
		InformacionTarea.establecerHorarioInicio(HI, MI);
	}
	
	public void establecerHorarioFin(int HF, int MF) throws HorariosEventoInvalidosException, HoraInvalidaException {
		InformacionTarea.establecerHorarioFin(HF, MF);
	}
	
	public void establecerLugar(String lugar) {
		InformacionTarea.establecerLugar(lugar);
	}
	
	public void establecerFecha(int dia, int mes, int anio) throws FechaInvalidaException {
		InformacionTarea.establecerFecha(dia, mes, anio);
	}
	
	public String obtenerID() {
		return ID;
	}
	
	public String obtenerTitulo() {
		return Titulo;
	}
	
	public String obtenerDescripcion() {
		return Descripcion;
	}
	
	public Voluntario obtenerVoluntario() {
		return Voluntario;
	}
	
	public Hora obtenerHorarioInicio() {
		return InformacionTarea.obtenerHorarioInicio();
	}
	
	public Hora obtenerHorarioFin() {
		return InformacionTarea.obtenerHorarioFin();
	}
	
	public String obtenerLugar() {
		return InformacionTarea.obtenerLugar();
	}
	
	public Fecha obtenerFecha() {
		return InformacionTarea.obtenerFecha();
	}
}
