package com.jornadas.client.Ventanas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.jornadas.client.ServicioAsync;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.usuario.Asistente;

public class VentanaActividadesDeAsistente extends VentanaEventoUsuario{

	protected ServicioAsync Servicio;
	protected Asistente Asistente;
	protected Map<String,Actividad> Actividades;
	
	public VentanaActividadesDeAsistente(Asistente asistente, ServicioAsync servicio) {
		super();
		Servicio=servicio;
		Nombre = "Mis Actividades";
		Asistente=asistente;
		Panel.getElement().getStyle().setBackgroundColor("#F5A9BC");
		
		Actividades = new HashMap<String,Actividad>();
		
		establecerListaDeActividades();
	}
	
	
	protected void establecerListaDeActividades() {
		listBoxEvento.addChangeHandler(new cambioActividad());
		
		Collection<Actividad> actividades = Asistente.obtenerActividadesInscriptas();
		
		for(Actividad actividad : actividades) {
			Actividades.put(actividad.obtenerTitulo(), actividad);
			listBoxEvento.addItem(actividad.obtenerTitulo());
		}
	}
	
	private class cambioActividad implements ChangeHandler {

		public void onChange(ChangeEvent event) {
			Actividad actividadElegida = Actividades.get(listBoxEvento.getSelectedItemText());
			if(actividadElegida!=null) {
				labelDescripcion.setText(actividadElegida.obtenerDescripcion());
				Fecha fechaActividad = actividadElegida.obtenerFecha();
				labelFecha.setText("Fecha: " + fechaActividad.obtenerDia() + "/" + fechaActividad.obtenerMes() + "/" + fechaActividad.obtenerAnio());
				Hora horaInicio = actividadElegida.obtenerHorarioInicio();
				Hora horaFin = actividadElegida.obtenerHorarioFin();
				labelHora.setText("Inicio: " + horaInicio.obtenerHora() + ":" + horaInicio.obtenerMinutos() + " - Fin: " + horaFin.obtenerHora() + ":" + horaFin.obtenerMinutos());
				labelLugar.setText(actividadElegida.obtenerLugar());
			}
		}
		
	}
}
