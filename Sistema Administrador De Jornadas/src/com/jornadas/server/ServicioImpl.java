package com.jornadas.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jornadas.client.Servicio;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.Usuario;

@SuppressWarnings("serial")
public class ServicioImpl extends RemoteServiceServlet implements Servicio {
	
	private Jornada jornada;
	
	public ServicioImpl() {
		jornada = new Jornada();
		Asistente a = new Asistente("1111","1234");
		a.establecerNombre("Yo");
		jornada.agregarUsuario(a);
	}
	
	public Usuario obtenerUsuario(String ID, String DNI) {
		return jornada.recuperarUsuario(ID, DNI);
	}

	public Boolean registrarUsuario(String ID, String DNI) {
		if(jornada.recuperarUsuario(ID, DNI)==null) {
			jornada.agregarUsuario(new Asistente(ID, DNI));
			return true;
		} else
			return false;
	}
}
