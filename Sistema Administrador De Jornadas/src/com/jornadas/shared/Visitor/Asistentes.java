package com.jornadas.shared.Visitor;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class Asistentes extends Accion{
	
	private static final long serialVersionUID = -2899860711463208761L;
	protected Collection<Asistente> asistentes;

	public Asistentes() {
		asistentes = new LinkedHashSet<Asistente>();
	}
	
	public void Accionar(Asistente usuario) {
		asistentes.add(usuario);
	}

	public void Accionar(Voluntario usuario) {}

	public void Accionar(Organizador usuario) {}

	public void Accionar(CoordinadorDeArea usuario) {}

	public void Accionar(OrganizadorGeneral usuario) {}

	public Collection<Asistente> obtenerAsistentes(){
		return asistentes;
	}
}
