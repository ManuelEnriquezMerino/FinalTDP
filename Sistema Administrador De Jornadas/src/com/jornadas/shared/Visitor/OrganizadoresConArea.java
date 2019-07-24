package com.jornadas.shared.Visitor;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class OrganizadoresConArea extends Accion{

	private static final long serialVersionUID = 4491108700960789033L;
	protected Collection<Organizador> Organizadores;
	
	public OrganizadoresConArea() {
		Organizadores = new LinkedHashSet<Organizador>();
	}
	public void Accionar(Asistente usuario) {}

	public void Accionar(Voluntario usuario) {}

	public void Accionar(Organizador usuario) {
		Organizadores.add(usuario);
	}

	public void Accionar(CoordinadorDeArea usuario) {
		Organizadores.add(usuario);
	}

	public void Accionar(OrganizadorGeneral usuario) {
		Organizadores.add(usuario);
	}
	
	public Collection<Organizador> obtenerOrganizadores(){
		return Organizadores;
	}

}
