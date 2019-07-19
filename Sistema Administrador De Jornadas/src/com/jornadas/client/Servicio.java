package com.jornadas.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.usuario.Usuario;

@RemoteServiceRelativePath("servicio")

public interface Servicio extends RemoteService{

	public Usuario obtenerUsuario(String DNI, String ID);
	
	public Boolean registrarUsuario(String DNI, String ID);
	
	public Boolean actualizarUsuario(Usuario usuario);
	
	public int obtenerIDNuevaArea();
	
	public Collection<Usuario> obtenerUsuarios();
	
	public Collection<TipoActividad> obtenerTiposDeActividades();
	
	public Boolean agregarArea(Area NuevaArea);
}
