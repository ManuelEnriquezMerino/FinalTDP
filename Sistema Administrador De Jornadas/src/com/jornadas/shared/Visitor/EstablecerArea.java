package com.jornadas.shared.Visitor;

import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;
import com.jornadas.shared.actividad.Area;

public class EstablecerArea extends Accion{
	
	private static final long serialVersionUID = 6558432511177555065L;
	protected Area Area;
	protected boolean resultado;
	
	public EstablecerArea(Area area) {
		Area=area;
	}
	
	public void Accionar(Asistente usuario) {}

	public void Accionar(Voluntario usuario) {}

	public void Accionar(Organizador usuario) {
		resultado = usuario.establecerArea(Area);
	}

	public void Accionar(CoordinadorDeArea usuario) {
		resultado = usuario.establecerArea(Area);
	}

	public void Accionar(OrganizadorGeneral usuario) {
		resultado = usuario.establecerArea(Area);
	}
	
	public boolean obtenerResultado() {
		return resultado;
	}
}
