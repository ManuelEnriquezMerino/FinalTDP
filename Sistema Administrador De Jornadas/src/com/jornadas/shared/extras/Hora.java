package com.jornadas.shared.extras;

import java.io.Serializable;

import com.jornadas.shared.excepciones.HoraInvalidaException;

public class Hora implements Serializable{

	private static final long serialVersionUID = -1900044918962743880L;
	protected int H;
	protected int M;
	
	public Hora() {}
	
	public Hora(int h, int m) throws HoraInvalidaException{
		if(horaValida(h)) {
			H=h;
			if(minutoValido(m))
				M=m;
		}
	}
	
	public void establecerHora(int h) throws HoraInvalidaException{
		if(horaValida(h))
			H=h;
	}
	
	public void establecerMinutos(int m) throws HoraInvalidaException{
		if(minutoValido(m))
			M=m;
	}
	
	public int obtenerHora() {
		return H;
	}
	
	public int obtenerMinutos() {
		return M;
	}

	private boolean horaValida(int h) throws HoraInvalidaException{
		if(h>=0 && h<=24)
			return true;
		else
			throw new HoraInvalidaException("ERROR: Hora Invalida");
	}
	
	private boolean minutoValido(int m) throws HoraInvalidaException{
		if(m>=0 && m<60)
			return true;
		else
			throw new HoraInvalidaException("ERROR: Minutos Invalidos");
	}
}
