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
		if(anio>0)
			Anio=anio;
		else
			throw new FechaInvalidaException("Error: El año no puede ser menor a 0");
		
		if(mes>0 && mes <=12)
			Mes=mes;
		else
			throw new FechaInvalidaException("Error: Mes Invalido");
		
		if(Mes==1 || Mes==3 || Mes==5 || Mes==7 || Mes==8 || Mes==10 || Mes==12 ) {
			if(Dia>0 && Dia<=31)
				Dia=dia;
			else
				throw new FechaInvalidaException("Error: Dia Invalido");
		} else {
			if(Mes==2) {
				if( ((Anio%4)==0 && (Anio%100)!=0) || (Anio%400)==0) {
					if(dia>0 && dia<=29)
						Dia=dia;
					else
						throw new FechaInvalidaException("Error: Dia Invalido");
				} else {
					if(dia>0 && dia<=28)
						Dia=dia;
					else
						throw new FechaInvalidaException("Error: Dia Invalido");
				}
			} else { //El mes es 4,6,9 o 11
				if(Dia>0 && Dia<=30)
					Dia=dia;
				else
					throw new FechaInvalidaException("Error: Dia Invalido");
			}
		}
	}
	
	public void establecerDia(int dia) throws FechaInvalidaException{
		if(Mes==1 || Mes==3 || Mes==5 || Mes==7 || Mes==8 || Mes==10 || Mes==12 ) {
			if(Dia>0 && Dia<=31)
				Dia=dia;
			else
				throw new FechaInvalidaException("Error: Dia Invalido");
		} else {
			if(Mes==2) {
				if( ((Anio%4)==0 && (Anio%100)!=0) || (Anio%400)==0) {
					if(dia>0 && dia<=29)
						Dia=dia;
					else
						throw new FechaInvalidaException("Error: Dia Invalido");
				} else {
					if(dia>0 && dia<=28)
						Dia=dia;
					else
						throw new FechaInvalidaException("Error: Dia Invalido");
				}
			} else { //El mes es 4,6,9 o 11
				if(Dia>0 && Dia<=30)
					Dia=dia;
				else
					throw new FechaInvalidaException("Error: Dia Invalido");
			}
		}
	}
	
	public void establecerMes(int mes) throws FechaInvalidaException{
		if(mes>0 && mes <=12)
			Mes=mes;
		else
			throw new FechaInvalidaException("Error: Mes Invalido");
	}
	
	public void establecerAnio(int anio) throws FechaInvalidaException{
		if(anio>0)
			Anio=anio;
		else
			throw new FechaInvalidaException("Error: El año no puede ser menor a 0");
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
}