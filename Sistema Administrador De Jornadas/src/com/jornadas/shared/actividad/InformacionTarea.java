package com.jornadas.shared.actividad;

import java.io.Serializable;

import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.excepciones.HoraInvalidaException;
import com.jornadas.shared.excepciones.HorariosEventoInvalidosException;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;

public class InformacionTarea implements Serializable{

	private static final long serialVersionUID = 2165500088965920781L;
	protected Hora HorarioInicio;
	protected Hora HorarioFin;
	protected String Lugar;
	protected Fecha Fecha;
	
	public InformacionTarea() {}
	
	public void establecerHorarioInicio(int HI, int MI) throws HorariosEventoInvalidosException, HoraInvalidaException {
		if(verificarHoraInicioHoraFin(HI,MI,HorarioFin.obtenerHora(),HorarioFin.obtenerMinutos())) {
			HorarioInicio = new Hora(HI,MI);
		}
	}
	
	public void establecerHorarioFin(int HF, int MF) throws HorariosEventoInvalidosException, HoraInvalidaException {
		if(verificarHoraInicioHoraFin(HorarioInicio.obtenerHora(),HorarioFin.obtenerMinutos(),HF,MF)) {
			HorarioFin = new Hora(HF,MF);
		}
	}
	
	public void establecerLugar(String lugar) {
		Lugar=lugar;
	}
	
	public void establecerFecha(int dia, int mes, int anio) throws FechaInvalidaException {
		Fecha = new Fecha(dia,mes,anio);
	}
	
	public Hora obtenerHorarioInicio() {
		return HorarioInicio;
	}
	
	public Hora obtenerHorarioFin() {
		return HorarioFin;
	}
	
	public String obtenerLugar() {
		return Lugar;
	}
	
	public Fecha obtenerFecha() {
		return Fecha;
	}
	
	private boolean verificarHoraInicioHoraFin(int HI, int MI,int HF, int MF) throws HorariosEventoInvalidosException {
		if(HI>HF) {
			throw new HorariosEventoInvalidosException("ERROR: La hora de inicio del evento no puede ser mayor a la hora de fin del evento");
		} else {
			if(HI==HF && MI>MF) {
				throw new HorariosEventoInvalidosException("ERROR: Los Minutos de inicio del evento no pueden ser mayor a los minutos de fin del evento si las horas son iguales");
			} else {
				return true;
			}
		}
	}
}
