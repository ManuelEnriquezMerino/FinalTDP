package com.jornadas.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jornadas.shared.usuario.Usuario;

@RemoteServiceRelativePath("servicio")

public interface Servicio extends RemoteService{

	public Usuario obtenerUsuario(String DNI, String ID);
	
	public Boolean registrarUsuario(String DNI, String ID);
	
	public Boolean actualizarUsuario(Usuario usuario);
}
