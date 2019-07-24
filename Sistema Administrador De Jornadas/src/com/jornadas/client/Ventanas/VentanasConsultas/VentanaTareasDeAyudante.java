package com.jornadas.client.Ventanas.VentanasConsultas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaEventoUsuario;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.usuario.Voluntario;

public class VentanaTareasDeAyudante extends VentanaEventoUsuario{

	protected ServicioAsync Servicio;
	protected Voluntario Ayudante;
	protected Map<String,Tarea> Tareas;
	
	public VentanaTareasDeAyudante(Voluntario ayudante, ServicioAsync servicio) {
		super();
		Servicio=servicio;
		Nombre = "Mis Actividades";
		Ayudante=ayudante;
		Panel.getElement().getStyle().setBackgroundColor("#F5A9BC");
		
		Tareas = new HashMap<String,Tarea>();
		
		establecerListaDeActividades();
	}
	
	
	protected void establecerListaDeActividades() {
		listBoxEvento.addChangeHandler(new cambioActividad());
		
		Collection<Tarea> tareas = Ayudante.obtenerTareas();
		
		for(Tarea tarea : tareas) {
			Tareas.put(tarea.obtenerTitulo(), tarea);
			listBoxEvento.addItem(tarea.obtenerTitulo());
		}
	}
	
	private class cambioActividad implements ChangeHandler {

		public void onChange(ChangeEvent event) {
			Tarea tareaElegida = Tareas.get(listBoxEvento.getSelectedItemText());
			if(tareaElegida!=null) {
				labelDescripcion.setText(tareaElegida.obtenerDescripcion());
				Fecha fechaActividad = tareaElegida.obtenerFecha();
				labelFecha.setText("Fecha: " + fechaActividad.obtenerDia() + "/" + fechaActividad.obtenerMes() + "/" + fechaActividad.obtenerAnio());
				Hora horaInicio = tareaElegida.obtenerHorarioInicio();
				Hora horaFin = tareaElegida.obtenerHorarioFin();
				labelHora.setText("Inicio: " + horaInicio.obtenerHora() + ":" + horaInicio.obtenerMinutos() + " - Fin: " + horaFin.obtenerHora() + ":" + horaFin.obtenerMinutos());
				labelLugar.setText(tareaElegida.obtenerLugar());
			}
		}
	}
	
}
