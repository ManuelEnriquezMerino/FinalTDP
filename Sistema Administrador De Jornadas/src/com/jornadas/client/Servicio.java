package com.jornadas.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.tarea.CreadorTarea;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.usuario.Usuario;

@RemoteServiceRelativePath("servicio")

public interface Servicio extends RemoteService{

	public Usuario obtenerUsuario(String DNI, String ID);
	
	public String registrarAsistente(String ID);
	
	public Boolean actualizarUsuario(Usuario usuario);
	
	public int obtenerIDNuevaArea();
	
	public Collection<Usuario> obtenerUsuarios();
	
	public Collection<TipoActividad> obtenerTiposDeActividades();
	
	public Collection<CreadorTarea> obtenerTiposDeTareas();
	
	public Boolean agregarArea(Area NuevaArea);
	
	public Boolean modificarArea(Area AreaModificada);
	
	public String agregarActividad(Actividad NuevaActividad);
	
	public String agregarTarea(Tarea NuevaTarea);
}
