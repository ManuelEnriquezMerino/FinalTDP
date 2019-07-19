package com.jornadas.shared.usuario;

import com.jornadas.shared.Visitor.Accion;

public class OrganizadorGeneral extends CoordinadorDeArea{

	private static final long serialVersionUID = 8069108207443157683L;

	public OrganizadorGeneral() {
		super();
	}
	
	public OrganizadorGeneral(String id, String dni) {
		super(id, dni);
	}

	public void accionar(Accion accion) {
		accion.Accionar(this);
	}
}
