package com.jornadas.server;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import com.jornadas.shared.Visitor.EstablecerArea;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.usuario.Usuario;

public class Jornada implements Serializable{

	private static final long serialVersionUID = -5891511464715397196L;
	private Map<String, Usuario> ColeccionDeUsuarios;
	private Collection<TipoActividad> TiposDeActividades;
	private Collection<Area> Areas;
	
	//Constructor
	
	public Jornada() {
		ColeccionDeUsuarios= new HashMap<String,Usuario>();
		TiposDeActividades = new LinkedHashSet<TipoActividad>();
		Areas = new LinkedHashSet<Area>();
	}
	
	
	//Metodos
	
	public void agregarUsuario(Usuario usuario) {
		String Clave = usuario.obtenerID() + usuario.obtenerDNI();
		ColeccionDeUsuarios.put(Clave, usuario);
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
			ColeccionDeUsuarios.get(Clave).accionar(accion);
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
	
	
	//Consultas
	
	public Usuario recuperarUsuario(String ID, String DNI) {
		String Clave = ID+DNI;
		return ColeccionDeUsuarios.get(Clave);
	}
	
	public Collection<Usuario> obtenerUsuarios() {
		return new LinkedList<Usuario>(ColeccionDeUsuarios.values());
	}
	
	public Collection<TipoActividad> obtenerTiposDeActividades() {
		return TiposDeActividades;
	}
	
	public int obtenerNuevoIDArea() {
		return (Areas.size()+1);
	}
}
