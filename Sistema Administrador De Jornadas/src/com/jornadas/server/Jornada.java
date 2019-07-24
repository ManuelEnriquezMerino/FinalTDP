package com.jornadas.server;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import com.jornadas.shared.Visitor.EstablecerArea;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.tarea.creadoresDeTareas.CreadorTarea;
import com.jornadas.shared.tarea.creadoresDeTareas.CreadorTareaExterna;
import com.jornadas.shared.usuario.Usuario;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorAyudanteEvento;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorCoordinadorDeArea;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorOrganizador;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorOrganizadorGeneral;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorVoluntario;

public class Jornada implements Serializable{

	private static final long serialVersionUID = -5891511464715397196L;
	protected String Nombre;
	protected String Descripcion;
	//protected InformacionJornada InformacionJornada;
	//protected Collection<Seleccionable> Servicios;
	//protected Collection<Seleccionable> Caracteristicas;
	protected Collection<CreadorAyudanteEvento> TiposDeAyudantes;
	//protected Collection<TipoAsistente> TiposDeAsistentes;
	protected Map<String, Usuario> Usuarios;
	protected Collection<Area> Areas;
	protected Collection<TipoActividad> TiposDeActividades;
	protected Collection<Actividad> Actividades;
	//protected Collection<ActividadObligatoria> ActividadesObligatorias;
	protected Collection<Tarea> Tareas;
	protected Collection<CreadorTarea> TiposDeTareas;
	
	//Constructor
	
	public Jornada() {
		Usuarios= new HashMap<String,Usuario>();
		TiposDeActividades = new LinkedHashSet<TipoActividad>();
		Areas = new LinkedHashSet<Area>();
		Actividades = new LinkedHashSet<Actividad>();
		Tareas = new LinkedHashSet<Tarea>();
		inicializarTiposDeTareas();
		inicializarTiposDeAyudantes();
	}
	
	
	//Metodos
	
	public void establecerNombre(String nombre) {
		Nombre = nombre;
	}
	
	public void establecerDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	public void agregarUsuario(Usuario usuario) {
		String Clave = usuario.obtenerID() + usuario.obtenerDNI();
		Usuarios.put(Clave, usuario);
	}
	
	public boolean modificarArea(Area areaModificada){
		boolean encontre = false;
		String idArea = areaModificada.obtenerID();
		Area AreaActual = null;
		Iterator<Area> iterador = Areas.iterator();
		
		while (!encontre && iterador.hasNext()) {
			AreaActual = iterador.next();
			encontre = idArea.equals(AreaActual.obtenerID());
		}
		
		if(encontre) {
			AreaActual.establecerNombre(areaModificada.obtenerNombre());
			if(AreaActual.establecerCoordinador(areaModificada.obtenerCoordinador()))
				return true;
			else
				return false;
		}
		
		return false;
	}
	
	public boolean agregarArea(Area area) {
		if (Areas.add(area)) {
			String Clave = area.obtenerCoordinador().obtenerID()+area.obtenerCoordinador().obtenerDNI();
			EstablecerArea accion = new EstablecerArea(area);
			Usuarios.get(Clave).accionar(accion);
			if(accion.obtenerResultado())
				return true;
			else
				Areas.remove(area);
		}
		return false;
	}
	
	public boolean agregarTipoActividad(TipoActividad tipo) {
		return TiposDeActividades.add(tipo);
	}
	
	public boolean agregarActividad(Actividad NuevaActividad) {
		return Actividades.add(NuevaActividad);
	}
	
	public boolean agregarTarea(Tarea NuevaTarea) {
		return Tareas.add(NuevaTarea);
	}
	
	//Consultas
	
	public String obtenerNombre() {
		return Nombre;
	}
	
	public String obtenerDescripcion() {
		return Descripcion;
	}
	
	public Usuario recuperarUsuario(String ID, String DNI) {
		String Clave = ID+DNI;
		return Usuarios.get(Clave);
	}
	
	public Collection<Usuario> obtenerUsuarios() {
		return new LinkedList<Usuario>(Usuarios.values());
	}
	
	public Collection<Actividad> obtenerActividades() {
		return Actividades;
	}
	
	public Collection<TipoActividad> obtenerTiposDeActividades() {
		return TiposDeActividades;
	}
	
	public Collection<CreadorTarea> obtenerTiposDeTareas(){
		return TiposDeTareas;
	}
	
	public Collection<CreadorAyudanteEvento> obtenerTiposDeAyudantes(){
		return TiposDeAyudantes;
	}
	
	public int obtenerNuevoIDUsuario() {
		return (Usuarios.size()+1);
	}
	
	public int obtenerNuevoIDArea() {
		return (Areas.size()+1);
	}
	
	public int obtenerNuevoIDActividad() {
		return (Actividades.size()+1);
	}
	
	public int obtenerNuevoIDTarea() {
		return (Tareas.size()+1);
	}
	
	public boolean existeUsuario(String DNI) {
		boolean existe = false;
		Iterator<Usuario> iterador = Usuarios.values().iterator();
		while(iterador.hasNext() && !existe) {
			existe=iterador.next().obtenerDNI().equals(DNI);
		}
		return existe;
	}
	
	
	//Metodos Privados
	
	protected void inicializarTiposDeTareas() {
		TiposDeTareas = new LinkedHashSet<CreadorTarea>();
		TiposDeTareas.add(new CreadorTareaExterna());
	}
	
	protected void inicializarTiposDeAyudantes() {
		TiposDeAyudantes = new LinkedHashSet<CreadorAyudanteEvento>();
		TiposDeAyudantes.add(new CreadorVoluntario());
		TiposDeAyudantes.add(new CreadorOrganizador());
		TiposDeAyudantes.add(new CreadorCoordinadorDeArea());
		TiposDeAyudantes.add(new CreadorOrganizadorGeneral());
	}
}
