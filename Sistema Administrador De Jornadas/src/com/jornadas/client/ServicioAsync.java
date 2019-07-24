package com.jornadas.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.tarea.creadoresDeTareas.CreadorTarea;
import com.jornadas.shared.usuario.Usuario;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorAyudanteEvento;

public interface ServicioAsync {

	public void obtenerUsuario(String DNI, String ID, AsyncCallback<Usuario> Respuesta);
	
	public void registrarAsistente(String DNI, AsyncCallback<String> Respuesta);

	public void registrarAyudante(String DNI, CreadorAyudanteEvento Creador, AsyncCallback<String> Respuesta);
	
	public void actualizarUsuario(Usuario usuario, AsyncCallback<Boolean> Respuesta);
	
	public void inscribirAsistenteAActividad(String IDAsistente, String DNIAsistente, String IDActividad, AsyncCallback<Boolean> Respuesta);
	
	public void desinscribirAsistenteAActividad(String IDAsistente, String DNIAsistente, String IDActividad, AsyncCallback<Boolean> Respuesta);
	
	public void inscribirAyudanteATarea(String IDAyudante, String DNIAyudante, String IDTarea, AsyncCallback<Boolean> Respuesta);
	
	public void desinscribirAyudanteATarea(String IDAyudante, String DNIAyudante, String IDTarea, AsyncCallback<Boolean> Respuesta);
	
	public void obtenerIDNuevaArea(AsyncCallback<Integer> Respuesta);
	
	public void obtenerUsuarios(AsyncCallback<Collection<Usuario>> Respuesta);
	
	public void obtenerActividades(AsyncCallback<Collection<Actividad>> Respuesta);
	
	public void obtenerTareas(AsyncCallback<Collection<Tarea>> Respuesta);;
	
	public void obtenerTiposDeActividades(AsyncCallback<Collection<TipoActividad>> Respuesta);
	
	public void obtenerTiposDeTareas(AsyncCallback<Collection<CreadorTarea>> Respuesta);
	
	public void obtenerTiposDeAyudantes(AsyncCallback<Collection<CreadorAyudanteEvento>> Respuesta);
	
	public void agregarArea(Area NuevaArea, AsyncCallback<Boolean> Respuesta);
	
	public void modificarArea(Area AreaModificada, AsyncCallback<Boolean> Respuesta);
	
	public void agregarActividad(Actividad NuevaActividad, String IDArea, AsyncCallback<String> Respuesta);
	
	public void agregarTarea(Tarea NuevaTarea, AsyncCallback<String> Respuesta);
}
