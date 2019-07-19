package com.jornadas.shared.actividad;

import com.jornadas.shared.excepciones.CupoInvalidoException;

public class InformacionActividad extends InformacionTarea{

	private static final long serialVersionUID = 6046692167196423435L;
	protected int Cupo;
	
	public InformacionActividad() {}
	
	public void establecerCupo(int cupo) throws CupoInvalidoException {
		if(cupoValido(cupo))
			Cupo=cupo;
	}
	
	public int obtenerCupo() {
		return Cupo;
	}
	
	protected boolean cupoValido(int cupo) throws CupoInvalidoException {
		if(cupo>0)
			return true;
		else
			throw new CupoInvalidoException("ERROR: El cupo debe ser mayor a 0");
	}
}
