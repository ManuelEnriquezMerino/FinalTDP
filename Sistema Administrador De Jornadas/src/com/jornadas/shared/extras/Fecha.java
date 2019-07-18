package com.jornadas.shared.extras;

import java.io.Serializable;

import com.jornadas.shared.excepciones.FechaInvalidaException;

public class Fecha implements Serializable{

	private static final long serialVersionUID = 8332012622326708370L;
	private int Dia;
	private int Mes;
	private int Anio;
	
	public Fecha() {}
	
	public Fecha(int dia, int mes, int anio) throws FechaInvalidaException{
		if(anioValido(anio)) {
			Anio=anio;
			if(mesValido(mes)) {
				Mes=mes;
				if(diaValido(dia))
					Dia=dia;
			}
		}
	}
	
	public void establecerDia(int dia) throws FechaInvalidaException{
		if(diaValido(dia))
			Dia=dia;
	}
	
	public void establecerMes(int mes) throws FechaInvalidaException{
		if(mesValido(mes)) {
			int mesAux=Mes;
			try {
				Mes=mes;
				diaValido(Dia);
			} catch (FechaInvalidaException e) {
				Mes=mesAux;
				throw new FechaInvalidaException("ERROR: El Dia establecido no es valido con el nuevo Mes");
			}
		}
	}
	
	public void establecerAnio(int anio) throws FechaInvalidaException{
		if(anioValido(anio)) {
			int anioAux=Anio;
			try {
				Anio=anio;
				diaValido(Dia);
			} catch (FechaInvalidaException e) {
				Anio=anioAux;
				throw new FechaInvalidaException("Error: El Dia establecido no es valido con el nuevo Año");
			}
		}
	}
	
	public int obtenerDia() {
		return Dia;
	}
	
	public int obtenerMes() {
		return Mes;
	}
	
	public int obtenerAnio() {
		return Anio;
	}
	
	private boolean anioValido(int anio) throws FechaInvalidaException {
		if(anio>0)
			return true;
		else
			throw new FechaInvalidaException("Error: El año no puede ser menor a 0");
	}
	
	private boolean mesValido(int mes) throws FechaInvalidaException {
		if(mes>0 && mes <=12)
			return true;
		else
			throw new FechaInvalidaException("Error: Mes Invalido");
	}
	
	private boolean diaValido(int dia) throws FechaInvalidaException {
		if(Mes==1 || Mes==3 || Mes==5 || Mes==7 || Mes==8 || Mes==10 || Mes==12 ) {
			if(Dia>0 && Dia<=31)
				return true;
			else
				throw new FechaInvalidaException("Error: Dia Invalido");
		} else {
			if(Mes==2) {
				if( ((Anio%4)==0 && (Anio%100)!=0) || (Anio%400)==0) {
					if(dia>0 && dia<=29)
						return true;
					else
						throw new FechaInvalidaException("Error: Dia Invalido");
				} else {
					if(dia>0 && dia<=28)
						return true;
					else
						throw new FechaInvalidaException("Error: Dia Invalido");
				}
			} else {
				//El mes es 4,6,9 o 11
				if(Mes==4 || Mes==6 || Mes==9 || Mes==11) {
					if(Dia>0 && Dia<=30)
						return true;
					else
						throw new FechaInvalidaException("Error: Dia Invalido");
				} else
					throw new FechaInvalidaException("Error: Mes Invalido");
			}
		}
	}
}