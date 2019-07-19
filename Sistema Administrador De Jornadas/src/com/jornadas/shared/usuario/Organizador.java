package com.jornadas.shared.usuario;

import com.jornadas.shared.Visitor.Accion;
import com.jornadas.shared.actividad.Area;

public class Organizador extends Voluntario{
	
	private static final long serialVersionUID = 1384555305901560482L;
	protected Area Area;

	public Organizador() {
		super();
	}
	
	public Organizador(String id, String dni) {
		super(id, dni);
	}
	
	public boolean establecerArea(Area area) {
		if(Area==null) {
			Area = area;
			return true;
		}
		else
			return false;
	}
	
	public void quitarArea() {
		Area = null;
	}
	
	public Area obtenerArea() {
		return Area;
	}
	
	public void accionar(Accion accion) {
		accion.Accionar(this);
	}
	
	public boolean tieneArea() {
		return Area!=null;
	}
}
