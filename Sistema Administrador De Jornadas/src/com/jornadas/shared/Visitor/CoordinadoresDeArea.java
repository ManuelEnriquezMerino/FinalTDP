package com.jornadas.shared.Visitor;

import java.util.HashMap;
import java.util.Map;

import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class CoordinadoresDeArea extends Accion{

	private static final long serialVersionUID = 4721102849234953864L;
	private Map<String,CoordinadorDeArea> Coordinadores;

	public CoordinadoresDeArea() {
		Coordinadores = new HashMap<String,CoordinadorDeArea>();
	}
	
	public void Accionar(Asistente usuario) {}

	public void Accionar(Voluntario usuario) {}

	public void Accionar(Organizador usuario) {}

	public void Accionar(CoordinadorDeArea usuario) {
		String clave = usuario.obtenerNombre() + " " + usuario.obtenerApellido();
		Coordinadores.put(clave, usuario);
	}

	public void Accionar(OrganizadorGeneral usuario) {
		String clave = usuario.obtenerNombre() + " " + usuario.obtenerApellido();
		Coordinadores.put(clave, usuario);
	}
	
	public Map<String,CoordinadorDeArea> obtenerColeccionCoordinadores(){
		return Coordinadores;
	}
}
