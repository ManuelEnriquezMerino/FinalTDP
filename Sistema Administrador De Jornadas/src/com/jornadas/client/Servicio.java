package com.jornadas.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.tarea.creadoresDeTareas.CreadorTarea;
import com.jornadas.shared.usuario.Usuario;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorAyudanteEvento;

@RemoteServiceRelativePath("servicio")

public interface Servicio extends RemoteService{

	public Usuario obtenerUsuario(String DNI, String ID);
	
	public String registrarAsistente(String DNI);
	
	public String registrarAyudante(String DNI, CreadorAyudanteEvento Creador);

	public Boolean actualizarUsuario(Usuario usuario);
	
	public Boolean inscribirAsistenteAActividad(String IDAsistente, String DNIAsistente, String IDActividad);
	
	public Boolean desinscribirAsistenteAActividad(String IDAsistente, String DNIAsistente, String IDActividad);
	
	public Boolean inscribirAyudanteATarea(String IDAyudante, String DNIAyudante, String IDTarea);
	
	public Boolean desinscribirAyudanteATarea(String IDAyudante, String DNIAyudante, String IDTarea);
	
	public int obtenerIDNuevaArea();
	
	public Collection<Usuario> obtenerUsuarios();
	
	public Collection<Actividad> obtenerActividades();
	
	public Collection<Tarea> obtenerTareas();
	
	public Collection<TipoActividad> obtenerTiposDeActividades();
	
	public Collection<CreadorTarea> obtenerTiposDeTareas();
	
	public Collection<CreadorAyudanteEvento> obtenerTiposDeAyudantes();
	
	public Boolean agregarArea(Area NuevaArea);
	
	public Boolean modificarArea(Area AreaModificada);
	
	public String agregarActividad(Actividad NuevaActividad, String IDArea);
	
	public String agregarTarea(Tarea NuevaTarea);
}
