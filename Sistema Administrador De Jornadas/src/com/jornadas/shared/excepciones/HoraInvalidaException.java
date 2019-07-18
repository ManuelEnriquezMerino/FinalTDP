package com.jornadas.shared.excepciones;

public class HoraInvalidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public HoraInvalidaException(String msg) {
		super(msg);
	}
}
