package com.jornadas.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jornadas.shared.usuario.Usuario;

public interface ServicioAsync {

	public void obtenerUsuario(String DNI, String ID, AsyncCallback<Usuario> Respuesta);
	
	public void registrarUsuario(String DNI, String ID, AsyncCallback<Boolean> Respuesta);
}
