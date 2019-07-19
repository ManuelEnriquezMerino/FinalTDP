package com.jornadas.shared.Visitor;

import java.io.Serializable;

import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public abstract class Accion implements Serializable{

	private static final long serialVersionUID = 5025913148693551697L;

	public abstract void Accionar(Asistente usuario);
	
	public abstract void Accionar(Voluntario usuario);
	
	public abstract void Accionar(Organizador usuario);
	
	public abstract void Accionar(CoordinadorDeArea usuario);
	
	public abstract void Accionar(OrganizadorGeneral usuario);
}
